// Copyright (C) 2023-2024, M. Yang
//
//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU General Public License as published by
//     the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.
//
//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU General Public License for details.
//
//     For further details, please see the README.md file included
//     with this software.

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
