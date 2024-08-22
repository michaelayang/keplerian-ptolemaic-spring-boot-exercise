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

@Entity
@Table(name="truth_data", schema="public")
public class TruthDataRecord {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column
    private long id;

    @Column(name = "truth_angle")
    private float truthAngle;

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public float getTruthAngle() {
        return truthAngle;
    }
    
    public void setTruthAngle(float truthAngle) {
        this.truthAngle = truthAngle;
    }

    @Override
    public String toString() {
        return "TruthDataRecord [truthAngle=" + truthAngle
                + "]";
    }
} 
