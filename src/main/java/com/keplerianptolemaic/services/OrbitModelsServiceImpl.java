package com.keplerianptolemaic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keplerianptolemaic.data.KeplerianRecordRepository;
import com.keplerianptolemaic.data.PtolemaicRecordRepository;
import com.keplerianptolemaic.data.TruthDataRecordRepository;
import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;

@Service
public class OrbitModelsServiceImpl implements OrbitModelsService {
    
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
    
    @Override
    public List<KeplerianRecord> getAllKeplerianRecords() {
        return keplerianRecordRepository.findAllKeplerianRecords();
    }

    @Override
    public KeplerianRecord getKeplerianRecord(Long id) {
        return keplerianRecordRepository.findKeplerianRecord(id);
    }
    
    @Override
    public PtolemaicRecord getPtolemaicRecord(Long id) {
        return ptolemaicRecordRepository.findPtolemaicRecord(id);        
    }
    
    @Override
    public TruthDataRecord getTruthDataRecord(Long id) {
        return truthDataRecordRepository.findTruthDataRecord(id);
    }
}
