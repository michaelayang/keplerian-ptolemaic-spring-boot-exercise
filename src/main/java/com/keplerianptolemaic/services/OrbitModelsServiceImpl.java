package com.keplerianptolemaic.services;

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
import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;

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

    public OrbitModelsServiceImpl(KeplerianRecordRepository keplerianRecordRepository,
                                  PtolemaicRecordRepository ptolemaicRecordRepository,
                                  TruthDataRecordRepository truthDataRecordRepository) {
        this.keplerianRecordRepository = keplerianRecordRepository;
        this.ptolemaicRecordRepository = ptolemaicRecordRepository;
        this.truthDataRecordRepository = truthDataRecordRepository;
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
}
