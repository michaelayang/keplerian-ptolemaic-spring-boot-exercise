package com.keplerianptolemaic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="keplerian", schema="public")
public class KeplerianRecord {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private long id;
    
    @Column(name = "earth_x")
    private float earthX;
    
    @Column(name = "earth_y")
    private float earthY;
    
    @Column(name = "earth_radius")
    private float earthRadius;
    
    @Column(name = "earth_theta")
    private float earthTheta;
    
    @Column(name = "mars_x")
    private float marsX;
    
    @Column(name = "mars_y")
    private float marsY;
    
    @Column(name = "mars_radius")
    private float marsRadius;
    
    @Column(name = "mars_theta")
    private float marsTheta;

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
 
    public float getEarthX() {
        return earthX;
    }
    
    public void setEarthX(float earthX) {
        this.earthX = earthX;
    }
    
    public float getEarthY() {
        return earthY;
    }
    
    public void setEarthY(float earthY) {
        this.earthY = earthY;
    }

    public float getEarthRadius() {
        return earthRadius;
    }
    
    public void setEarthRadius(float earthRadius) {
        this.earthRadius = earthRadius;
    }

    public float getEarthTheta() {
        return earthTheta;
    }
    
    public void setEarthTheta(float earthTheta) {
        this.earthTheta = earthTheta;
    }

    public float getMarsX() {
        return marsX;
    }
    
    public void setMarsX(float marsX) {
        this.marsX = marsX;
    }
    
    public float getMarsY() {
        return marsY;
    }
    
    public void setMarsY(float marsY) {
        this.marsY = marsY;
    }

    public float getMarsRadius() {
        return marsRadius;
    }
    
    public void setMarsRadius(float marsRadius) {
        this.marsRadius = marsRadius;
    }

    public float getMarsTheta() {
        return marsTheta;
    }
    
    public void setMarsTheta(float marsTheta) {
        this.marsTheta = marsTheta;
    }

    @Override
    public String toString() {
        return "KeplerianRecord [earthX=" + earthX + ", earthY=" + earthY
                + ", earthRadius=" + earthRadius + ", earthTheta=" + earthTheta 
                + ", marsX=" + marsX + ", marsY=" + marsY
                + ", marsRadius=" + marsRadius + ", marsTheta=" + marsTheta
                + "]";
    }
} 
