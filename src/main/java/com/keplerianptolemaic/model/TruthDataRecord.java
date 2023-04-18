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
