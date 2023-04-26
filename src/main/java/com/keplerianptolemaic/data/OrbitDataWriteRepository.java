package com.keplerianptolemaic.data;


public interface OrbitDataWriteRepository {

    void clearKeplerianRecords();
    void insertKeplerianRecord(long id, float earthX, float earthY, float earthRadius, float earthTheta, float marsX, float marsY, float marsRadius, float marsTheta);

    void clearPtolemaicRecords();
    void insertPtolemaicRecord(long id, float firstEpicycleTheta, float firstEpicycleRadius, float secondEpicycleTheta, float secondEpicycleRadius, float thirdEpicycleTheta, float thirdEpicycleRadius, float ptolemaicOverallAngle);

    void clearTruthDataRecords();
    void insertTruthDataRecord(long id, float truthAngle);
}
