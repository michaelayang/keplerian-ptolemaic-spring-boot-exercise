package com.keplerianptolemaic.model;

import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

public class KeplerianPredictor implements Predictor {

    private EarthKeplerianEllipse earthKeplerianEllipse = new EarthKeplerianEllipse();
    private MarsKeplerianEllipse marsKeplerianEllipse = new MarsKeplerianEllipse();

    private double[] earthXArray = new double[(int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR];
    private double[] earthYArray = new double[(int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR];

    private double[] marsXArray = new double[(int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR];
    private double[] marsYArray = new double[(int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR];
    
    public KeplerianPredictor() throws SemiMajorAxisInvalidException, EccentricityInvalidException {
        this.earthKeplerianEllipse = new EarthKeplerianEllipse();
        this.marsKeplerianEllipse = new MarsKeplerianEllipse();
        
        for (int i = 0; i < (int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR; i++) {
            earthXArray[i] = earthKeplerianEllipse.getX();
            earthYArray[i] = earthKeplerianEllipse.getY();
            earthKeplerianEllipse.step();
        }
        
        for (int i = 0; i < (int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR; i++) {
            marsXArray[i] = marsKeplerianEllipse.getX();
            marsYArray[i] = marsKeplerianEllipse.getY();
            marsKeplerianEllipse.step();
        }
    }
    
    public double getMarsTheta(long timeId) {
        int earthIndex = (int)(timeId%EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR);
        int marsIndex = (int)(timeId%MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR);
        
        if (earthIndex < 0 || earthIndex > (int)EarthKeplerianEllipse.EARTH_DAYS_IN_EARTH_YEAR) {
            throw new IllegalArgumentException("Illegal earthIndex of " + earthIndex);
        }
        
        double earthX = earthXArray[earthIndex];
        double earthY = earthYArray[earthIndex];

        if (marsIndex < 0 || marsIndex > (int)MarsKeplerianEllipse.EARTH_DAYS_IN_MARS_YEAR) {
            throw new IllegalArgumentException("Illegal marsIndex of " + marsIndex);
        }
        
        double marsX = marsXArray[earthIndex];
        double marsY = marsYArray[earthIndex];
        
        return Math.atan2(marsY-earthY, marsX-earthX);
    }
} 
