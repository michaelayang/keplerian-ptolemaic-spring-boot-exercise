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
        return Math.toDegrees(keplerianPredictionContext.getPredictionAngle(date));
    }

    @Override
    public double getPtolemaicPredictionAngle(Date date) {
        return Math.toDegrees(ptolemaicPredictionContext.getPredictionAngle(date));
    }
}
