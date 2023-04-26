package com.keplerianptolemaic.services;

import java.util.List;

import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;

public interface OrbitModelsService {
    List<KeplerianRecord> getAllKeplerianRecords();
    KeplerianRecord getKeplerianRecord(Long id);
    PtolemaicRecord getPtolemaicRecord(Long id);
    TruthDataRecord getTruthDataRecord(Long id);
    boolean loadKeplerianRecords(List<String> data);
    boolean loadPtolemaicRecords(List<String> data);
    boolean loadTruthDataRecords(List<String> data);
}
