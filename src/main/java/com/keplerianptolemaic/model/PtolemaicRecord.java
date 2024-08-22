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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="ptolemaic", schema="public")
@Data
public class PtolemaicRecord {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private long id;

    @Column(name = "first_epicycle_theta")
    private float firstEpicycleTheta;
    
    @Column(name = "first_epicycle_radius")
    private float firstEpicycleRadius;
    
    @Column(name = "second_epicycle_theta")
    private float secondEpicycleTheta;
    
    @Column(name = "second_epicycle_radius")
    private float secondEpicycleRadius;
    
    @Column(name = "third_epicycle_theta")
    private float thirdEpicycleTheta;
    
    @Column(name = "third_epicycle_radius")
    private float thirdEpicycleRadius;
    
    @Column(name = "ptolemaic_overall_angle")
    private float ptolemaicOverallAngle;

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
 
    public float getFirstEpicycleTheta() {
        return firstEpicycleTheta;
    }
    
    public void setFirstEpicycleTheta(float firstEpicycleTheta) {
        this.firstEpicycleTheta = firstEpicycleTheta;
    }
    
    public float getFirstEpicycleRadius() {
        return firstEpicycleRadius;
    }
    
    public void setFirstEpicycleRadius(float firstEpicycleRadius) {
        this.firstEpicycleRadius = firstEpicycleRadius;
    }

    public float getSecondEpicycleTheta() {
        return secondEpicycleTheta;
    }
    
    public void setSecondEpicycleTheta(float secondEpicycleTheta) {
        this.secondEpicycleTheta = secondEpicycleTheta;
    }

    public float getSecondEpicycleRadius() {
        return secondEpicycleRadius;
    }
    
    public void setSecondEpicycleRadius(float secondEpicycleRadius) {
        this.secondEpicycleRadius = secondEpicycleRadius;
    }

    public float getThirdEpicycleTheta() {
        return thirdEpicycleTheta;
    }
    
    public void setThirdEpicycleTheta(float thirdEpicycleTheta) {
        this.thirdEpicycleTheta = thirdEpicycleTheta;
    }
    
    public float getThirdEpicycleRadius() {
        return thirdEpicycleRadius;
    }
    
    public void setThirdEpicycleRadius(float thirdEpicycleRadius) {
        this.thirdEpicycleRadius = thirdEpicycleRadius;
    }

    public float getPtolemaicOverallAngle() {
        return ptolemaicOverallAngle;
    }
    
    public void setPtolemaicOverallAngle(float ptolemaicOverallAngle) {
        this.ptolemaicOverallAngle = ptolemaicOverallAngle;
    }

    @Override
    public String toString() {
        return "PtolemaicRecord [firstEpicycleTheta=" + firstEpicycleTheta + ", firstEpicycleRadius=" + firstEpicycleRadius
                + ", secondEpicycleTheta=" + secondEpicycleTheta + ", secondEpicycleRadius=" + secondEpicycleRadius 
                + ", thirdEpicycleTheta=" + thirdEpicycleTheta + ", thirdEpicycleRadius=" + thirdEpicycleRadius
                + ", ptolemaicOverallAngle=" + ptolemaicOverallAngle
                + "]";
    }
} 
