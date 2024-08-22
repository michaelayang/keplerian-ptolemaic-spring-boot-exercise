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

public class PtolemaicPredictor implements Predictor {

    private static double PTOLEMAIC_ORBIT_RADIUS = 7.54676e+31;
    private static double PTOLEMAIC_ORBIT_THETA = 2.051476;
    private static double PTOLEMAIC_ORBIT_PERIOD = 710.561;
    private static double PTOLEMAIC_EPICYCLE_RADIUS = 5.35e+31;
    private static double PTOLEMAIC_EPICYCLE_THETA = 5.173851;
    private static double PTOLEMAIC_EPICYCLE_PERIOD = 371.61;
    private static double PTOLEMAIC_EPICYCLE2_RADIUS = 9.83639e+30;
    private static double PTOLEMAIC_EPICYCLE2_THETA = 1.343858;
    private static double PTOLEMAIC_EPICYCLE2_PERIOD = 4451.06;
    
    private static int EARTH_MARS_SYNODIC_PERIOD_IN_EARTH_DAYS = 780;

    private double[] marsThetaArray = new double[EARTH_MARS_SYNODIC_PERIOD_IN_EARTH_DAYS];

    public PtolemaicPredictor() {
        PtolemaicOrbit ptolemaicOrbit = new PtolemaicOrbit(PTOLEMAIC_ORBIT_RADIUS, PTOLEMAIC_ORBIT_THETA, PTOLEMAIC_ORBIT_PERIOD, null);
    
        PtolemaicOrbit ptolemaicEpicycle =
                             new PtolemaicOrbit(PTOLEMAIC_EPICYCLE_RADIUS, PTOLEMAIC_EPICYCLE_THETA, PTOLEMAIC_EPICYCLE_PERIOD,
                                                ptolemaicOrbit);
    
        PtolemaicOrbit ptolemaicEpicycle2 =
                             new PtolemaicOrbit(PTOLEMAIC_EPICYCLE2_RADIUS, PTOLEMAIC_EPICYCLE2_THETA, PTOLEMAIC_EPICYCLE2_PERIOD,
                                                ptolemaicEpicycle);
    
        for (int i = 0; i < EARTH_MARS_SYNODIC_PERIOD_IN_EARTH_DAYS; i++) {
            marsThetaArray[i] = ptolemaicEpicycle2.getOverallAngle();
    
            ptolemaicOrbit.step();
            ptolemaicEpicycle.step();
            ptolemaicEpicycle2.step();
        }
    }

    public double getMarsTheta(long timeId) {
        int marsIndex = (int)(timeId%EARTH_MARS_SYNODIC_PERIOD_IN_EARTH_DAYS);

        return marsThetaArray[marsIndex];
    }
}
