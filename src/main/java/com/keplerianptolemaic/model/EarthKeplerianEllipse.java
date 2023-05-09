package com.keplerianptolemaic.model;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

class EarthKeplerianEllipse extends KeplerianEllipse {

    static double EARTH_DAYS_IN_EARTH_YEAR = 365.256363004;
    
    EarthKeplerianEllipse() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        super(149.598023e9, // semi-major axis distance in meters;
              -1.16817, // this is the angle of the semi-major axis itself
              0.0167086, // eccentricity is a unitless ratio
              3.01685, // Start Earth at the this radian position
              1.0/EARTH_DAYS_IN_EARTH_YEAR // The numStepsReciprocalSeed specified here is 1/(the number of days in an Earth year)
        );
    }
};
