package com.keplerianptolemaic.model;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

public class KeplerianPredictorTest extends PredictorTest {

    private static final double ERROR_TOLERANCE_DEGREES = 2.5;
     
    public KeplerianPredictorTest(long timeId, double expectedMarsTheta) {
        super(timeId, expectedMarsTheta);
    }

    @Override
    public void initialize() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        predictor = new KeplerianPredictor();
    }

    @Override
    protected double getErrorTolerance() {
        return ERROR_TOLERANCE_DEGREES;
    }
}
