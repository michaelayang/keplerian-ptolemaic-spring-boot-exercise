package com.keplerianptolemaic.model;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

class KeplerianEllipse {

    //
    // Assume sun is at (0,0).
    // All angles are in radians.
    //

    //
    // For the following...
    //
    // semiMajorAxisLength and eccentricity should be googleable on
    // Wikipedia, e.g. for Mars and Earth.  Just plug them in from those
    // figures.  Use meters as length units.
    //

    private static double INCREMENT_SAMPLE_ANGLE = ((Math.PI/180.0)/100.0); // 1/100 a degree

    private double ellipseA;
    private double ellipseB;
    private double ellipseC;
    private double eccentricity;
    private double directrix;
    private double axisAngle;

    private double polarCoordTheta;
    private double polarCoordRadius;
    private double sweepArea;
    private double polarCoordAverageRadius;
    private double areaOfEllipse;

    private int sweepCount;
    private double accruedArea;

    public KeplerianEllipse(double semiMajorAxisLength, // "a" ... in meters
                            double semiMajorAxisAngle, // in radians
                            double eccentricity, // b=sqrt(a^2-c^2),c=e*a
                            double initialTheta, // in radians
                            double numStepsReciprocalSeed) throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        this.ellipseA = semiMajorAxisLength;
        this.axisAngle = semiMajorAxisAngle;
        this.eccentricity = eccentricity;
        this.polarCoordTheta = initialTheta;
 
        if (ellipseA > 0)
        {
            ellipseC = eccentricity*ellipseA;
        } else {
            throw new SemiMajorAxisInvalidException();
        }
      
        if (ellipseA > ellipseC) {
            ellipseB = Math.sqrt(Math.pow(ellipseA, 2.0) - Math.pow(ellipseC, 2.0));
        } else {
            throw new EccentricityInvalidException();
        }
      
        if (eccentricity != 0) {
            directrix = (ellipseA/eccentricity)-ellipseC;
        } else {
            directrix = ellipseA;
        }
      
        polarCoordRadius = getDerivedRadiusFromTheta(polarCoordTheta);
      
        //
        // From "Calculus With Analytic Geometry" by George F. Simmons
        // Copyright 1985
        // Problem 5 in section 15.3 for chapter "Ellipses," which points out
        // how the area of an ellipse is related to the area of a circle
        // by the factor b/a, when the circle equation is x^2 + y^2 = a^2 and
        // the ellipse equation is x^2/a^2 + y^2/b^2 = 1.  It works out when
        // you do the algebra; very interesting!
        //
        double areaOfCircleWithRadiusA = Math.PI*ellipseA*ellipseA;
      
        areaOfEllipse = areaOfCircleWithRadiusA*ellipseB/ellipseA;
      
        polarCoordAverageRadius = Math.sqrt(areaOfEllipse/Math.PI);
      
        sweepArea = numStepsReciprocalSeed;
       
        sweepArea *= areaOfEllipse; // Normalize area-per-step-to-sweep,
                                    // so that number of sweep steps will be
                                    // the same for all ellipses.
      
        this.sweepCount = 1;
        this.accruedArea = 0.0;

    }// KeplerianEllipse constructor

    public void step() {
        double step_area = 0.0;
        double radius1 = polarCoordRadius;
        double theta1 = polarCoordTheta;

        //
        // Derived per info in
        // "Calculus With Analytic Geometry" by George F. Simmons
        // Copyright 1985
        // Section 16.3 "Polar Equations of Circles, Conics, and Spirals and
        // section 16.5 "Areas in Polar Coordinates" and section 15.3 Ellipses
        //
        double p = eccentricity != 0.0 ? (ellipseA/eccentricity) - (eccentricity*ellipseA) : ellipseA;

        while (accruedArea + step_area < sweepArea*sweepCount)
        {
            theta1 += INCREMENT_SAMPLE_ANGLE;
            radius1 = getDerivedRadiusFromTheta(theta1);
            double dTheta = INCREMENT_SAMPLE_ANGLE;
            double dA = eccentricity != 0.0 ? 0.5*dTheta*Math.pow((eccentricity*p)/(1.0-eccentricity*Math.cos(theta1)), 2.0) : 0.5*ellipseA*ellipseA*dTheta;
            step_area += dA;
        }

        polarCoordRadius = radius1;
        polarCoordTheta = theta1;

        accruedArea += step_area;
        sweepCount++;

    }// step()

    public double getAverageRadius() {
        return polarCoordAverageRadius;

    }// getAverageRadius()

    public double getX() {
        return polarCoordRadius*Math.cos(polarCoordTheta+axisAngle);

    }// getX()

    public double getY() {
        return polarCoordRadius*Math.sin(polarCoordTheta+axisAngle);

    }// getY()

    public double getTheta() {
        return polarCoordTheta;

    }// getTheta()

    public double getRadius() {
        return polarCoordRadius;

    }// getRadius()

    private double getDerivedRadiusFromTheta(double theta)
    {
        double retVal = 0.0;

        //
        // From "Calculus With Analytic Geometry" by George F. Simmons
        // Copyright 1985
        // Section 16.3 "Polar Equations of Circles, Conics, and Spirals"
        //
        if (eccentricity != 0)
        {
            retVal = (eccentricity*directrix)/(1-eccentricity*Math.cos(theta));
        } else {
            retVal = ellipseA;
        }

        return retVal;

    }// getDerivedRadiusFromTheta()

};
