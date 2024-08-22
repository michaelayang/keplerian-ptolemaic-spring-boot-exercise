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

package com.keplerianptolemaic.services;

import java.util.Date;
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
    double getKeplerianPredictionAngle(Date date);
    double getPtolemaicPredictionAngle(Date date);
}
