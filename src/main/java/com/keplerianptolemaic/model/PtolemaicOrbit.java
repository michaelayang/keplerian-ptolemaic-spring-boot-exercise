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

public class PtolemaicOrbit {

    private double radius;
    private double theta;
    
    private double cartX;
    private double cartY;
    
    private double period;

    private PtolemaicOrbit parent;
    
    private int stepCount;

    public PtolemaicOrbit(double radius,
                          double theta,
                          double period,
                          PtolemaicOrbit parent) {
        this.radius = radius;
        this.theta = theta;
        this.period = period;
        this.parent = parent;
    
        convertPolarToCartesian(radius, theta);
    
        stepCount = (int)Math.round(period*theta/(2.0*Math.PI));
    }

    public double getRadius() {
        return radius;
    }
    
    public double getTheta() {
        return theta;
    }
    
    public double getPeriod() {
        return period;
    }
    
    public double getX() {
        double X = 0.0;
        if (parent != null) {
            X = parent.getX();
        }
        return X + cartX;
    }
    
    public double getY() {
        double Y = 0.0;
        if (parent != null) {
            Y = parent.getY();
        }
        return Y + cartY;
    }
    
    public double getOverallAngleUpToParent() {
        return parent.getOverallAngle();
    }
    
    public double getOverallAngle() {
        return Math.atan2(getY(), getX());
    }
    
    public void step() {
        theta = ((1.0/period)*(double)stepCount)*2.0*Math.PI;
        convertPolarToCartesian(radius, theta);
        stepCount++;
    }
    
    void convertPolarToCartesian(double radius, double theta) {
        cartX = radius*Math.cos(theta);
        cartY = radius*Math.sin(theta);
    }
    
    void convertCartesianToPolar(double x, double y) {
        radius = Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
        theta = Math.atan2(y, x);
    }    
}
