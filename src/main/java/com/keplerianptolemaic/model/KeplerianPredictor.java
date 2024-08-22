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

public class KeplerianPredictor implements Predictor {

    private EarthKeplerianEllipse earthKeplerianEllipse = new EarthKeplerianEllipse();
    private MarsKeplerianEllipse marsKeplerianEllipse = new MarsKeplerianEllipse();

    private double[] earthXArray = new double[(int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR+1];
    private double[] earthYArray = new double[(int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR+1];

    private double[] marsXArray = new double[(int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR+1];
    private double[] marsYArray = new double[(int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR+1];
    
    public KeplerianPredictor() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        this.earthKeplerianEllipse = new EarthKeplerianEllipse();
        this.marsKeplerianEllipse = new MarsKeplerianEllipse();
        
        for (int i = 0; i <= (int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR; i++) {
            earthXArray[i] = earthKeplerianEllipse.getX();
            earthYArray[i] = earthKeplerianEllipse.getY();
            earthKeplerianEllipse.step();
        }
        
        for (int i = 0; i <= (int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR; i++) {
            marsXArray[i] = marsKeplerianEllipse.getX();
            marsYArray[i] = marsKeplerianEllipse.getY();
            marsKeplerianEllipse.step();
        }
    }
    
    public double getMarsTheta(long timeId) {
        int earthIndex = (int)(timeId%EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR);
        int marsIndex = (int)(timeId%MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR);
        
        if (earthIndex < 0 || earthIndex > (int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR) {
            throw new IllegalArgumentException("Illegal earthIndex of " + earthIndex + " for timeId of " + timeId);
        }
        
        double earthX = earthXArray[earthIndex];
        double earthY = earthYArray[earthIndex];

        if (marsIndex < 0 || marsIndex > (int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR) {
            throw new IllegalArgumentException("Illegal marsIndex of " + marsIndex + " for timeId of " + timeId);
        }
        
        double marsX = marsXArray[marsIndex];
        double marsY = marsYArray[marsIndex];
        
        return Math.atan2(marsY-earthY, marsX-earthX);
    }
} 
