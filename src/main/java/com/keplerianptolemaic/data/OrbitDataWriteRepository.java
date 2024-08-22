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


public interface OrbitDataWriteRepository {

    void clearKeplerianRecords();
    void insertKeplerianRecord(long id, float earthX, float earthY, float earthRadius, float earthTheta, float marsX, float marsY, float marsRadius, float marsTheta);

    void clearPtolemaicRecords();
    void insertPtolemaicRecord(long id, float firstEpicycleTheta, float firstEpicycleRadius, float secondEpicycleTheta, float secondEpicycleRadius, float thirdEpicycleTheta, float thirdEpicycleRadius, float ptolemaicOverallAngle);

    void clearTruthDataRecords();
    void insertTruthDataRecord(long id, float truthAngle);
}
