package com.keplerianptolemaic.model;

public class PtolemaicPredictor implements Predictor {

    private static double PTOLEMAIC_ORBIT_RADIUS = 7.54676e+31;
    private static double PTOLEMAIC_ORBIT_THETA = 2.05745;
    private static double PTOLEMAIC_ORBIT_PERIOD = 710.561;
    private static double PTOLEMAIC_EPICYCLE_RADIUS = 5.35e+31;
    private static double PTOLEMAIC_EPICYCLE_THETA = 5.18254;
    private static double PTOLEMAIC_EPICYCLE_PERIOD = 371.61;
    private static double PTOLEMAIC_EPICYCLE2_RADIUS = 9.83639e+30;
    private static double PTOLEMAIC_EPICYCLE2_THETA = 1.34445;
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
