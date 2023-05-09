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
