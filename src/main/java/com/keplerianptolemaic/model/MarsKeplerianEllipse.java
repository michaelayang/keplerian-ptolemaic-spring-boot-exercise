package com.keplerianptolemaic.model;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

class MarsKeplerianEllipse extends KeplerianEllipse {

    static double EARTH_DAYS_IN_MARS_YEAR = 686.971;
    
    MarsKeplerianEllipse() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        super(227.9392e9, // semi-major axis distance in meters;
              1.15467, // this is the angle of the semi-major axis itself
              0.09341233, // eccentricity is a unitless ratio
              0.72244, // Start Mars at the thisradian position
              1/EARTH_DAYS_IN_MARS_YEAR // The numStepsReciprocalSeed specified here is 1/(the number of days in an Mars year)
        );
    }
};
