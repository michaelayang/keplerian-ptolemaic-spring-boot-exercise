angular.module('KeplerianPtolemaic', []).controller("mainController", function($scope, $http) {

  var sleep = function(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }

  var clearCanvas = function() {
    canvasContext.clearRect(0, 0, canvas.width, canvas.height);
  }

  var drawLine = function(x0, y0, x1, y1) {
    canvasContext.beginPath();

    canvasContext.strokeStyle = "black";

    canvasContext.moveTo((canvas.width/2) + x0, (canvas.height/2) - y0);
    canvasContext.lineTo((canvas.width/2) + x1, (canvas.height/2) - y1);

    canvasContext.stroke();
  }

  var drawDot = function(x, y, radius, color) {
    canvasContext.beginPath();

    canvasContext.strokeStyle = color;
    canvasContext.fillStyle   = color;

    canvasContext.arc((canvas.width/2) + x,
                      (canvas.height/2) - y,
                      radius, 0, 2*Math.PI);

    canvasContext.fill();
    canvasContext.stroke();
  }

  var drawEarth = function(x, y) {
    drawDot(x, y, 8, "blue");
  }

  var drawMars = function(x, y) {
    drawDot(x, y, 5, "red");
  }

  var drawBlackDot = function(x, y) {
    drawDot(x, y, 3, "black");
  }

  var drawSun = function(x, y) {
    drawDot(x, y, 16, "yellow");
  }
  
  var drawLightGenericDot = function(x, y) {
    drawDot(x, y, 2, "lightgray");
  }

  var drawOrbit = function(centerX, centerY, radius) {

    canvasContext.beginPath();
  
    canvasContext.strokeStyle   = "black";
  
    canvasContext.arc((canvas.width/2) + centerX,
                      (canvas.height/2) - centerY,
                      radius, 0, 2*Math.PI);
  
    canvasContext.stroke();
  };

  var drawErrorText = function(drawX, drawY, angleY, angleX, truthAngleStr, totalErrorMagnitude, totalNumRecords) {
    canvasContext.beginPath();

    canvasContext.strokeStyle   = "black";
    canvasContext.font = "18px Arial";

    var angleDeg = Math.atan2(angleY, angleX)*180.0/Math.PI;

    var diffAngle = parseFloat(truthAngleStr) - angleDeg;

    var text = "Current earth-day error:  " + diffAngle.toFixed(2) + " deg";

    canvasContext.strokeText(text,
                             (canvas.width/2) + drawX,
                             (canvas.height/2) - drawY);

    totalErrorMagnitude += Math.abs(diffAngle);

    text = "Total-error-magnitude:  " + totalErrorMagnitude.toFixed(2) + " deg / " + totalNumRecords + " earth-days";

    canvasContext.strokeText(text,
                             (canvas.width/2) + drawX,
                             (canvas.height/2) - drawY + 25);

    return totalErrorMagnitude;
  };

  var drawPtolemaic = function(fields, truthAngle, totalNumRecords) {
    drawEarth(-DRAWING_OFFSET, 0);
    var accumulatedX = -DRAWING_OFFSET;
    var accumulatedY = 0;
    for (var fieldIndex in fields) {
      if (fieldIndex < fields.length-1) {
        var field = fields[fieldIndex];
        var fieldTheta = field.split(",")[0].substring(1);
        var fieldRadius = field.split(",")[1];
        fieldRadius = fieldRadius.substring(0, fieldRadius.length-1);
        var orbitRadius = (maxOrbitDisplayRadius*(fieldRadius/maxPtolemaicOrbitRadius));
        if (orbitRadius < 0) {
            orbitRadius = -orbitRadius;
            if (fieldTheta < 0) {
                fieldTheta += Math.PI;
            }
            else {
                fieldTheta -= Math.PI;
            }
        }
        var x = Math.cos(fieldTheta)*orbitRadius;
        var y = Math.sin(fieldTheta)*orbitRadius;
        drawOrbit(accumulatedX, accumulatedY, orbitRadius);
        if (fieldIndex == fields.length-2) {
          drawLine(-DRAWING_OFFSET, 0, accumulatedX + x, accumulatedY + y);
          drawMars(accumulatedX + x, accumulatedY + y);
          totalPtolemaicError = drawErrorText(-DRAWING_OFFSET-TEXT_OFFSET_X, -TEXT_OFFSET_Y, accumulatedY + y, DRAWING_OFFSET + accumulatedX + x, truthAngle, totalPtolemaicError, totalNumRecords);
        }
        else {
          drawBlackDot(accumulatedX + x, accumulatedY + y);
        }
  
        accumulatedX += x;
        accumulatedY += y;
      }
    }
  }
  
  var drawKeplerian = function(fields, truthAngle, totalNumRecords) {
    var earthX = fields[0].split(",")[0].substring(1);
    var earthY = fields[0].split(",")[1];
    var marsX = fields[1].split(",")[0].substring(1);
    var marsY = fields[1].split(",")[1];

    earthX = maxOrbitDisplayRadius*(earthX/maxKeplerianOrbitRadius);
    earthY = maxOrbitDisplayRadius*(earthY/maxKeplerianOrbitRadius);
    marsX = maxOrbitDisplayRadius*(marsX/maxKeplerianOrbitRadius);
    marsY = maxOrbitDisplayRadius*(marsY/maxKeplerianOrbitRadius);

    drawSun(0 + DRAWING_OFFSET, 0);
    drawEarth(earthX + DRAWING_OFFSET, earthY);
    drawLine(earthX + DRAWING_OFFSET, earthY, marsX + DRAWING_OFFSET, marsY);
    drawMars(marsX + DRAWING_OFFSET, marsY);

    totalKeplerianError = drawErrorText(DRAWING_OFFSET-TEXT_OFFSET_X, -TEXT_OFFSET_Y, marsY - earthY, marsX - earthX, truthAngle, totalKeplerianError, totalNumRecords);
  }
  
  var clearKeplerian = function(fields) {
    var earthX = fields[0].split(",")[0].substring(1);
    var earthY = fields[0].split(",")[1];
    var marsX = fields[1].split(",")[0].substring(1);
    var marsY = fields[1].split(",")[1];

    earthX = maxOrbitDisplayRadius*(earthX/maxKeplerianOrbitRadius);
    earthY = maxOrbitDisplayRadius*(earthY/maxKeplerianOrbitRadius);
    marsX = maxOrbitDisplayRadius*(marsX/maxKeplerianOrbitRadius);
    marsY = maxOrbitDisplayRadius*(marsY/maxKeplerianOrbitRadius);

    drawLightGenericDot(earthX + DRAWING_OFFSET, earthY);
    drawLightGenericDot(marsX + DRAWING_OFFSET, marsY);
  }
 
  var drawKeplerianOrbits = function(allKeplerianRecords) {
    for (var index in allKeplerianRecords) {
      if (index < allKeplerianRecords.length-1) {
        var fields = allKeplerianRecords[index];
        clearKeplerian(fields);
      }
    }
  }
 
  var canvas                = document.getElementById("myCanvas");
  var canvasContext         = canvas.getContext("2d");
  var canvasAspectRatio     = 1/1;
  var maxOrbitDisplayRadius = 100;
  var maxPtolemaicOrbitRadius      = 7.546760e+31;
  var maxKeplerianOrbitRadius      = 1.75e+11;
  var ANIMATION_FRAME_PERIOD_MSECS = 1;
  var DRAWING_OFFSET        = canvas.width/5;
  var TEXT_OFFSET_X         = 185;
  var TEXT_OFFSET_Y         = 225;
  var totalPtolemaicError   = 0.0;
  var totalKeplerianError   = 0.0;
  var TOTAL_RECORDS         = 780;

  async function animate() {

    totalPtolemaicError = 0.0;
    totalKeplerianError = 0.0;

    var allKeplerianRecords = [];
    $http.get("http://localhost:8080/getAllKeplerianRecords")
      .then(function(allKeplerianResponse) {
      var allKeplerianArray = angular.fromJson(allKeplerianResponse.data);

      for (var i = 0; i < allKeplerianArray.length; i++) {
        var keplerianDataAssociativeArray = allKeplerianArray[i];
        var keplerianDataArray = []; 
        keplerianDataArray.push("(" + keplerianDataAssociativeArray["earthX"] 
                                + "," + keplerianDataAssociativeArray["earthY"]
                                + "," + keplerianDataAssociativeArray["earthRadius"]
                                + "," + keplerianDataAssociativeArray["earthTheta"]
                                + ")");
        keplerianDataArray.push("(" + keplerianDataAssociativeArray["marsX"] 
                                + "," + keplerianDataAssociativeArray["marsY"]
                                + "," + keplerianDataAssociativeArray["marsRadius"]
                                + "," + keplerianDataAssociativeArray["marsTheta"]
                                + ")");

        allKeplerianRecords.push(keplerianDataArray);
      }

      clearCanvas();
      canvasContext.strokeText("Finished loading allKeplerianRecords data array.  Reading individual Keplerian, Ptolemaic, and truth data records ... please standby...", 0, 25);

    });

    while (allKeplerianRecords.length < TOTAL_RECORDS) {
      await sleep(100);
    }

    var latestIndex = 0;

    for (var index = 0; index < TOTAL_RECORDS; index++) {

      $http.get("http://localhost:8080/getPtolemaicRecord/" + index)
        .then(function(ptolemaicResponse) {
            var ptolemaicLine = ptolemaicResponse.data;
            var ptolemaicDataAssociativeArray = [];
            ptolemaicDataAssociativeArray = angular.fromJson(ptolemaicLine);
 
            var ptolemaicDataArray = []; 
            ptolemaicDataArray.push("(" + ptolemaicDataAssociativeArray["firstEpicycleTheta"] 
                                    + "," + ptolemaicDataAssociativeArray["firstEpicycleRadius"] + ")");
            ptolemaicDataArray.push("(" + ptolemaicDataAssociativeArray["secondEpicycleTheta"] 
                                    + "," + ptolemaicDataAssociativeArray["secondEpicycleRadius"] + ")");
            ptolemaicDataArray.push("(" + ptolemaicDataAssociativeArray["thirdEpicycleTheta"] 
                                    + "," + ptolemaicDataAssociativeArray["thirdEpicycleRadius"] + ")");
            ptolemaicDataArray.push(ptolemaicDataAssociativeArray["ptolemaicOverallAngle"]);

            var latestId = ptolemaicDataAssociativeArray["id"];

            $http.get("http://localhost:8080/getKeplerianRecord/" + latestId)
              .then(function(keplerianResponse) {
                var keplerianLine = keplerianResponse.data;
                var keplerianDataAssociativeArray = [];
                keplerianDataAssociativeArray = angular.fromJson(keplerianLine);
 
                var keplerianDataArray = []; 
                keplerianDataArray.push("(" + keplerianDataAssociativeArray["earthX"] 
                                        + "," + keplerianDataAssociativeArray["earthY"]
                                        + "," + keplerianDataAssociativeArray["earthRadius"]
                                        + "," + keplerianDataAssociativeArray["earthTheta"]
                                        + ")");
                keplerianDataArray.push("(" + keplerianDataAssociativeArray["marsX"] 
                                        + "," + keplerianDataAssociativeArray["marsY"]
                                        + "," + keplerianDataAssociativeArray["marsRadius"]
                                        + "," + keplerianDataAssociativeArray["marsTheta"]
                                        + ")");
 
                var latestId = keplerianDataAssociativeArray["id"];

                $http.get("http://localhost:8080/getTruthDataRecord/" + latestId)
                  .then(function(truthDataResponse) {
                    var truthDataLine = truthDataResponse.data;
                    var truthDataAssociativeArray = [];
                    truthDataAssociativeArray = angular.fromJson(truthDataLine);

                    var truthDataArray = [];
                    truthDataArray.push(truthDataAssociativeArray["truthAngle"]);

                    var latestId = truthDataAssociativeArray["id"];

                    if (latestId > latestIndex) {
                      clearCanvas();
                      //drawPtolemaic(fields.slice(0,4), truthAngleStr, allKeplerianRecords.length-1);
                      drawPtolemaic(ptolemaicDataArray.slice(0,4), truthDataArray[0], allKeplerianRecords.length-1);
                      drawKeplerianOrbits(allKeplerianRecords);
                      //drawKeplerian(fields.slice(4,6), truthAngleStr, allKeplerianRecords.length-1);
                      drawKeplerian(keplerianDataArray, truthDataArray[0], allKeplerianRecords.length-1);

                      //canvasContext.strokeText(latestId, 50, canvas.height-100);

                      latestIndex = latestId;

                    }

                });
 
            });

      });

      await sleep(ANIMATION_FRAME_PERIOD_MSECS);

    }

  };

  $scope.startDemo = function() {

    clearCanvas();

    canvasContext.strokeStyle   = "black";
    canvasContext.font = "18px Arial";

    canvasContext.strokeText("The demo is starting up in a few moments...", 0, 25);

    animate();

  };

});
