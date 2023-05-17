package com.keplerianptolemaic.model;

public class PtolemaicPredictorTest extends PredictorTest {

    private static final double ERROR_TOLERANCE_DEGREES = 8.0;
 
    public PtolemaicPredictorTest(long timeId, double expectedMarsTheta) {
        super(timeId, expectedMarsTheta);
    }

    @Override
    public void initialize() {
        predictor = new PtolemaicPredictor();
    }

    @Override
    protected double getErrorTolerance() {
        return ERROR_TOLERANCE_DEGREES;
    }
}
