package com.keplerianptolemaic.controllers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keplerianptolemaic.model.KeplerianRecord;
import com.keplerianptolemaic.model.PtolemaicRecord;
import com.keplerianptolemaic.model.TruthDataRecord;
import com.keplerianptolemaic.services.OrbitModelsService;

@Controller
public class SolarSystemModelsController {

    @Autowired
    private OrbitModelsService orbitModelsService;

    @RequestMapping(value = "/getAllKeplerianRecords", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllKeplerianRecords() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<KeplerianRecord> keplerianRecords = orbitModelsService.getAllKeplerianRecords();
            String json = objectMapper.writeValueAsString(keplerianRecords);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Keplerian data returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getKeplerianRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getKeplerianRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            KeplerianRecord keplerianRecord = orbitModelsService.getKeplerianRecord(id);
            String json = objectMapper.writeValueAsString(keplerianRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Keplerian record returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getPtolemaicRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPtolemaicRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PtolemaicRecord ptolemaicRecord = orbitModelsService.getPtolemaicRecord(id);
            String json = objectMapper.writeValueAsString(ptolemaicRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Ptolemaic record returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getTruthDataRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTruthDataRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TruthDataRecord truthDataRecord = orbitModelsService.getTruthDataRecord(id);
            String json = objectMapper.writeValueAsString(truthDataRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Truth Data record returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/loadKeplerianRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public ResponseEntity<Object> loadKeplerianRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadKeplerianRecords(data);
            if (!loadSuccessful) {
                return new ResponseEntity<Object>("Keplerian data load failed", HttpStatus.BAD_REQUEST);                
            }
            return new ResponseEntity<Object>("Keplerian data load succeeded.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/loadPtolemaicRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public ResponseEntity<Object> loadPtolemaicRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadPtolemaicRecords(data);
            if (!loadSuccessful) {
                return new ResponseEntity<Object>("Ptolemaic data load failed", HttpStatus.BAD_REQUEST);              
            }
            return new ResponseEntity<Object>("Ptolemaic data load succeeded.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/loadTruthDataRecords", method = RequestMethod.PUT) // Note:  This endpoint is idempotent; therefore a PUT rather than a POST
    public ResponseEntity<Object> loadTruthDataRecords(@RequestBody List<String> data) {
        try {
            boolean loadSuccessful = orbitModelsService.loadTruthDataRecords(data);
            if (!loadSuccessful) {
                return new ResponseEntity<Object>("Truth data load failed", HttpStatus.BAD_REQUEST);              
            }
            return new ResponseEntity<Object>("Truth data load succeeded.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/getKeplerianPrediction/{date}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getKeplerianPrediction(@PathVariable @NotNull String date) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            double predictionAngle = orbitModelsService.getKeplerianPredictionAngle(Date.from(LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.UTC)));
            String json = objectMapper.writeValueAsString(predictionAngle);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Keplerian prediction returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = "/getPtolemaicPrediction/{date}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPtolemaicPrediction(@PathVariable @NotNull String date) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            double predictionAngle = orbitModelsService.getPtolemaicPredictionAngle(Date.from(LocalDate.parse(date).atStartOfDay().toInstant(ZoneOffset.UTC)));
            String json = objectMapper.writeValueAsString(predictionAngle);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>("No Ptolemaic prediction returned", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
