package com.keplerianptolemaic.services;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import com.keplerianptolemaic.data.KeplerianRecordRepository;
import com.keplerianptolemaic.data.PtolemaicRecordRepository;
import com.keplerianptolemaic.data.TruthDataRecordRepository;
import com.keplerianptolemaic.model.KeplerianPredictor;
import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicPredictor;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;
import com.keplerianptolemaic.utils.EccentricityInvalidException;
import com.keplerianptolemaic.utils.SemiMajorAxisInvalidException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@CacheConfig(cacheNames={"keplerianRecords", "keplerianRecord", "ptolemaicRecord", "truthDataRecord"})
public class OrbitModelsServiceImpl implements OrbitModelsService {
    
    private static final double AZIMUTH_HOURS_AT_MODEL_STARTING_DATE = 6.0;

    private static final double DECLINATION_DEGREES_AT_MODEL_STARTING_DATE = 23.4;

    Logger logger = LoggerFactory.getLogger(OrbitModelsServiceImpl.class);

    @Autowired
    private KeplerianRecordRepository keplerianRecordRepository;
    
    @Autowired
    private PtolemaicRecordRepository ptolemaicRecordRepository;
    
    @Autowired
    private TruthDataRecordRepository truthDataRecordRepository;

    private PredictionContext keplerianPredictionContext;
    private PredictionContext ptolemaicPredictionContext;

    public OrbitModelsServiceImpl(KeplerianRecordRepository keplerianRecordRepository,
                                  PtolemaicRecordRepository ptolemaicRecordRepository,
                                  TruthDataRecordRepository truthDataRecordRepository) throws SemiMajorAxisInvalidException, EccentricityInvalidException{
        this.keplerianRecordRepository = keplerianRecordRepository;
        this.ptolemaicRecordRepository = ptolemaicRecordRepository;
        this.truthDataRecordRepository = truthDataRecordRepository;
        
        keplerianPredictionContext = new PredictionContext(new KeplerianPredictor());
        ptolemaicPredictionContext = new PredictionContext(new PtolemaicPredictor());
    }

    @Cacheable("keplerianRecords")
    @Override
    public List<KeplerianRecord> getAllKeplerianRecords() {
        return keplerianRecordRepository.findAllKeplerianRecords();
    }

    @Cacheable("keplerianRecord")
    @Override
    public KeplerianRecord getKeplerianRecord(Long id) {
        return keplerianRecordRepository.findKeplerianRecord(id);
    }
    
    @Cacheable("ptolemaicRecord")
    @Override
    public PtolemaicRecord getPtolemaicRecord(Long id) {
        return ptolemaicRecordRepository.findPtolemaicRecord(id);        
    }
    
    @Cacheable("truthDataRecord")
    @Override
    public TruthDataRecord getTruthDataRecord(Long id) {
        logger.info("Queried truth data record with id: {}", id);
        return truthDataRecordRepository.findTruthDataRecord(id);
    }
    
    public boolean loadKeplerianRecords(List<String> data) {
        try {
            logger.info("Clearing old Keplerian records");
            
            keplerianRecordRepository.clearKeplerianRecords();

            logger.info("Starting to load Keplerian records");

            data.stream().forEach(line -> {
                String[] fields = line.split(",");
                long id = Long.valueOf(fields[0]);
                float earthX = Float.valueOf(fields[1]);
                float earthY = Float.valueOf(fields[2]);
                float earthRadius = Float.valueOf(fields[3]);
                float earthTheta = Float.valueOf(fields[4]);
                float marsX = Float.valueOf(fields[5]);
                float marsY = Float.valueOf(fields[6]);
                float marsRadius = Float.valueOf(fields[7]);
                float marsTheta = Float.valueOf(fields[8]);

                keplerianRecordRepository.insertKeplerianRecord(id, earthX, earthY, earthRadius, earthTheta, marsX, marsY, marsRadius, marsTheta);
            });
            
            logger.info("Finished loading Keplerian records");

        } catch (NumberFormatException nfe) {
            logger.error("Error in OrbitModelsService.loadKeplerianRecords() : {}", nfe.getMessage(), nfe);
        }
        return true;
    }

    public boolean loadPtolemaicRecords(List<String> data) {
        try {
            logger.info("Clearing old Ptolemaic records");

            ptolemaicRecordRepository.clearPtolemaicRecords();

            logger.info("Starting to load Ptolemaic records");

            data.stream().forEach(line -> {
                String[] fields = line.split(",");
                long id = Long.valueOf(fields[0]);
                float firstEpicycleTheta = Float.valueOf(fields[1]);
                float firstEpicycleRadius = Float.valueOf(fields[2]);
                float secondEpicycleTheta = Float.valueOf(fields[3]);
                float secondEpicycleRadius = Float.valueOf(fields[4]);
                float thirdEpicycleTheta = Float.valueOf(fields[5]);
                float thirdEpicycleRadius = Float.valueOf(fields[6]);
                float ptolemaicOverallAngle = Float.valueOf(fields[7]);

                ptolemaicRecordRepository.insertPtolemaicRecord(id, firstEpicycleTheta, firstEpicycleRadius, secondEpicycleTheta, secondEpicycleRadius, thirdEpicycleTheta, thirdEpicycleRadius, ptolemaicOverallAngle);
            });
            
            logger.info("Finished loading Ptolemaic records");

        } catch (NumberFormatException nfe) {
            logger.error("Error in OrbitModelsService.loadPtolemaicRecords() : {}", nfe.getMessage(), nfe);
        }
        return true;
    }
    
    public boolean loadTruthDataRecords(List<String> data) {
        try {
            logger.info("Clearing old Truth Data records");

            truthDataRecordRepository.clearTruthDataRecords();

            logger.info("Starting to load Truth Data records");

            data.stream().forEach(line -> {
                String[] fields = line.split(",");
                long id = Long.valueOf(fields[0]);
                float truthAngle = Float.valueOf(fields[1]);

                truthDataRecordRepository.insertTruthDataRecord(id, truthAngle);
            });
            
            logger.info("Finished loading Truth Data records");

        } catch (NumberFormatException nfe) {
            logger.error("Error in OrbitModelsService.loadTruthDataRecords() : {}", nfe.getMessage(), nfe);
        }
        return true;
    }

    @Override
    public double getKeplerianPredictionAngle(Date date) {
        return coordinateTransformAzimuthToSkyCoords(keplerianPredictionContext.getPredictionAngle(date));
    }

    @Override
    public double getPtolemaicPredictionAngle(Date date) {
        return coordinateTransformAzimuthToSkyCoords(ptolemaicPredictionContext.getPredictionAngle(date));
    }
    
    private double coordinateTransformAzimuthToSkyCoords(double orbitalPlaneAzimuth) {

        double dec0Radians = DECLINATION_DEGREES_AT_MODEL_STARTING_DATE*Math.PI/180.0;
        double ar0Radians = AZIMUTH_HOURS_AT_MODEL_STARTING_DATE*2.0*Math.PI/24.0;

        double basisDecX1 = 1.0*Math.cos(dec0Radians);
        double basisDecY1 = 0.0;
        double basisDecZ1 = 1.0*Math.sin(dec0Radians);
        double basisVectorX1 = basisDecX1*Math.cos(ar0Radians);
        double basisVectorY1 = basisDecX1*Math.sin(ar0Radians);
        double basisVectorZ1 = basisDecZ1;

        double basisDecX2 = 0.0;
        double basisDecY2 = 1.0;
        double basisDecZ2 = 0.0;
        double basisVectorX2 = -basisDecY2*Math.sin(ar0Radians);
        double basisVectorY2 = basisDecY2*Math.cos(ar0Radians);
        double basisVectorZ2 = basisDecZ2;

        double basisDecX3 = 1.0*Math.sin(dec0Radians);
        double basisDecY3 = 0.0;
        double basisDecZ3 = 1.0*Math.cos(dec0Radians);
        double basisVectorX3 = basisDecX3*Math.cos(ar0Radians);
        double basisVectorY3 = -basisDecX3*Math.sin(ar0Radians);
        double basisVectorZ3 = basisDecZ3;

        double decRadians = 0.0;
        double arRadians = orbitalPlaneAzimuth;
        double decX = 1.0*Math.cos(decRadians);
        double decY = 0;
        double decZ = 1.0*Math.sin(decRadians);
        double vectorX = decX*Math.cos(arRadians);
        double vectorY = decX*Math.sin(arRadians);
        double vectorZ = decZ;
 
        double basisMatrix[][] = new double[3][3];
        basisMatrix[0][0] = basisVectorX1;
        basisMatrix[0][1] = basisVectorY1;
        basisMatrix[0][2] = basisVectorZ1;
        basisMatrix[1][0] = basisVectorX2;
        basisMatrix[1][1] = basisVectorY2;
        basisMatrix[1][2] = basisVectorZ2;
        basisMatrix[2][0] = basisVectorX3;
        basisMatrix[2][1] = basisVectorY3;
        basisMatrix[2][2] = basisVectorZ3;

        double inversionBasisMatrix[][] = new double[3][3];
        invert3x3Matrix(basisMatrix, inversionBasisMatrix);

        double inversionBasisX1 = inversionBasisMatrix[0][0];
        double inversionBasisX2 = inversionBasisMatrix[1][0];
        double inversionBasisX3 = inversionBasisMatrix[2][0];
        double inversionBasisY1 = inversionBasisMatrix[0][1];
        double inversionBasisY2 = inversionBasisMatrix[1][1];
        double inversionBasisY3 = inversionBasisMatrix[2][1];
        double inversionBasisZ1 = inversionBasisMatrix[0][2];
        double inversionBasisZ2 = inversionBasisMatrix[1][2];
        double inversionBasisZ3 = inversionBasisMatrix[2][2];

        double invertedCoordTransformX = inversionBasisX1*vectorX + inversionBasisY1*vectorY + inversionBasisZ1*vectorZ;
        double invertedCoordTransformY = inversionBasisX2*vectorX + inversionBasisY2*vectorY + inversionBasisZ2*vectorZ;
        double invertedCoordTransformZ = inversionBasisX3*vectorX + inversionBasisY3*vectorY + inversionBasisZ3*vectorZ;

        logger.debug("inverse-transformed vector was [ {}, {}, {} ]", invertedCoordTransformX, invertedCoordTransformY, invertedCoordTransformZ);

        double ar = Math.atan2(invertedCoordTransformY, invertedCoordTransformX);
        if (ar < 0) {
            ar += (2.0*Math.PI);
        }
        double dec = Math.atan2(invertedCoordTransformZ, Math.sqrt(invertedCoordTransformX*invertedCoordTransformX + invertedCoordTransformY*invertedCoordTransformY));

        logger.debug("original ar was {}", arRadians*24.0/(2.0*Math.PI));
        logger.debug("ar was {}", ar*24.0/(2.0*Math.PI));

        return ar*24.0/(2.0*Math.PI);
    }

    private void invert3x3Matrix(double[][] matrix, double[][] invertedMatrix) {
        double determinant = matrix[0][0]*matrix[1][1]*matrix[2][2]
                             + matrix[0][1]*matrix[1][2]*matrix[2][0]
                             + matrix[0][2]*matrix[1][0]*matrix[2][1]
                             - matrix[0][2]*matrix[1][1]*matrix[2][0]
                             - matrix[0][1]*matrix[1][0]*matrix[2][2]
                             - matrix[0][0]*matrix[1][2]*matrix[2][1];

        invertedMatrix[0][0] = matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1];
        invertedMatrix[0][1] = matrix[0][2]*matrix[2][1] - matrix[0][1]*matrix[2][2];
        invertedMatrix[0][2] = matrix[0][1]*matrix[1][2] - matrix[0][2]*matrix[1][1];

        invertedMatrix[1][0] = matrix[1][2]*matrix[2][0] - matrix[1][0]*matrix[2][2];
        invertedMatrix[1][1] = matrix[0][0]*matrix[2][2] - matrix[0][2]*matrix[2][0];
        invertedMatrix[1][2] = matrix[0][2]*matrix[1][0] - matrix[0][0]*matrix[1][2];

        invertedMatrix[2][0] = matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][0];
        invertedMatrix[2][1] = matrix[0][1]*matrix[2][0] - matrix[0][0]*matrix[2][1];
        invertedMatrix[2][2] = matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                invertedMatrix[i][j] *= (1.0/determinant);
            }
        }
    }
}
