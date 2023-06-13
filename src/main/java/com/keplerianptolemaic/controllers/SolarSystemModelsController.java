package com.keplerianptolemaic.controllers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestBody;

import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;
import com.keplerianptolemaic.services.OrbitModelsService;

@RestController
public class SolarSystemModelsController {

    @Autowired
    private OrbitModelsService orbitModelsService;

    @RequestMapping(value = "/getAllKeplerianRecords", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<KeplerianRecord> getAllKeplerianRecords() {
        List<KeplerianRecord> keplerianRecords = orbitModelsService.getAllKeplerianRecords();
        if (keplerianRecords == null || keplerianRecords.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Keplerian data returned");
        }
        return keplerianRecords;
    }

    @RequestMapping(value = "/getKeplerianRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public KeplerianRecord getKeplerianRecord(@PathVariable @NotNull Long id) {
        KeplerianRecord keplerianRecord = orbitModelsService.getKeplerianRecord(id);
        if (keplerianRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Keplerian record returned");
        }
        return keplerianRecord;
    }

    @RequestMapping(value = "/getPtolemaicRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public PtolemaicRecord getPtolemaicRecord(@PathVariable @NotNull Long id) {
        PtolemaicRecord ptolemaicRecord = orbitModelsService.getPtolemaicRecord(id);
        if (ptolemaicRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Ptolemaic record returned");
        }
        return ptolemaicRecord;
    }

    @RequestMapping(value = "/getTruthDataRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public TruthDataRecord getTruthDataRecord(@PathVariable @NotNull Long id) {
        TruthDataRecord truthDataRecord = orbitModelsService.getTruthDataRecord(id);
        if (truthDataRecord == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Truth Data record returned");
        }
        return truthDataRecord;
    }

    @RequestMapping(value = "/loadKeplerianRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public String loadKeplerianRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadKeplerianRecords(data);
            if (!loadSuccessful) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Keplerian data load failed");                
            }
            return "Keplerian data load succeeded.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/loadPtolemaicRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public String loadPtolemaicRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadPtolemaicRecords(data);
            if (!loadSuccessful) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ptolemaic data load failed");              
            }
            return "Ptolemaic data load succeeded.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    @RequestMapping(value = "/loadTruthDataRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public String loadTruthDataRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadTruthDataRecords(data);
            if (!loadSuccessful) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Truth data load failed");              
            }
            return "Truth data load succeeded.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    @RequestMapping(value = "/getKeplerianPrediction/{date}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public double getKeplerianPrediction(@PathVariable @NotNull String date) {
        try {
            double predictionAngle = orbitModelsService.getKeplerianPredictionAngle(Date.from(LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.UTC)));
            return predictionAngle;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    
    @RequestMapping(value = "/getPtolemaicPrediction/{date}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public double getPtolemaicPrediction(@PathVariable @NotNull String date) {
        try {
            double predictionAngle = orbitModelsService.getPtolemaicPredictionAngle(Date.from(LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.UTC)));
            return predictionAngle;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
