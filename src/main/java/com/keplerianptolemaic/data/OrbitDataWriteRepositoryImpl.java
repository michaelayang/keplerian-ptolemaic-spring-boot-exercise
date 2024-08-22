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

package com.keplerianptolemaic.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrbitDataWriteRepositoryImpl implements OrbitDataWriteRepository {
    
    Logger logger = LoggerFactory.getLogger(OrbitDataWriteRepositoryImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void clearKeplerianRecords() {
        try {
            entityManager.createNativeQuery("DELETE FROM public.keplerian;").executeUpdate();
        } catch (Exception e) {
            logger.error("clearKeplerianRecords : {}", e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void insertKeplerianRecord(long id, float earthX, float earthY, float earthRadius, float earthTheta, float marsX, float marsY, float marsRadius, float marsTheta) {
        try {
            entityManager.createNativeQuery("INSERT INTO public.keplerian (id, earth_x, earth_y, earth_radius, earth_theta, mars_x, mars_y, mars_radius, mars_theta) VALUES (?,?,?,?,?,?,?,?,?);")
                .setParameter(1, id)
                .setParameter(2, earthX)
                .setParameter(3, earthY)
                .setParameter(4, earthRadius)
                .setParameter(5, earthTheta)
                .setParameter(6, marsX)
                .setParameter(7, marsY)
                .setParameter(8, marsRadius)
                .setParameter(9, marsTheta)
                .executeUpdate();
        } catch (Exception e) {
            logger.error("insertKeplerianRecord error :  {}", e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void clearPtolemaicRecords() {
        try {
            entityManager.createNativeQuery("DELETE FROM public.ptolemaic;").executeUpdate();
        } catch (Exception e) {
            logger.error("clearPtolemaicRecords : {}", e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void insertPtolemaicRecord(long id, float firstEpicycleTheta, float firstEpicycleRadius, float secondEpicycleTheta, float secondEpicycleRadius, float thirdEpicycleTheta, float thirdEpicycleRadius, float ptolemaicOverallAngle) {
        try {
            entityManager.createNativeQuery("INSERT INTO public.ptolemaic (id, first_epicycle_theta, first_epicycle_radius, second_epicycle_theta, second_epicycle_radius, third_epicycle_theta, third_epicycle_radius, ptolemaic_overall_angle) VALUES (?,?,?,?,?,?,?,?);")
                .setParameter(1, id)
                .setParameter(2, firstEpicycleTheta)
                .setParameter(3, firstEpicycleRadius)
                .setParameter(4, secondEpicycleTheta)
                .setParameter(5, secondEpicycleRadius)
                .setParameter(6, thirdEpicycleTheta)
                .setParameter(7, thirdEpicycleRadius)
                .setParameter(8, ptolemaicOverallAngle)
                .executeUpdate();
        } catch (Exception e) {
            logger.error("insertPtolemaicRecord error :  {}", e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void clearTruthDataRecords() {
        try {
            entityManager.createNativeQuery("DELETE FROM public.truth_data;").executeUpdate();
        } catch (Exception e) {
            logger.error("clearPtolemaicRecords : {}", e.getMessage(), e);
        }
    }
    
    @Transactional
    @Override
    public void insertTruthDataRecord(long id, float truthAngle) {
        try {
            entityManager.createNativeQuery("INSERT INTO public.truth_data (id, truth_angle) VALUES (?,?);")
                .setParameter(1, id)
                .setParameter(2, truthAngle)
                .executeUpdate();
        } catch (Exception e) {
            logger.error("insertPtolemaicRecord error :  {}", e.getMessage(), e);
        }
    }
}
