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
