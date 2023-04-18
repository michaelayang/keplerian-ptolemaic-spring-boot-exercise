package com.keplerianptolemaic.controllers;

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

//    @RequestMapping(value = "/loadKeplerianPtolemaicData", method = RequestMethod.POST)
//    public ResponseEntity<Object> loadKeplerianPtolemaicData(List<String> data) {
//
//        return new ResponseEntity<Object>(orbitModelsService.load(data), HttpStatus.CREATED);
//
//    }

    @RequestMapping(value = "/getAllKeplerianRecords", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllKeplerianRecords() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<KeplerianRecord> keplerianRecords = orbitModelsService.getAllKeplerianRecords();
            String json = objectMapper.writeValueAsString(keplerianRecords);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getKeplerianRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getKeplerianRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            KeplerianRecord keplerianRecord = orbitModelsService.getKeplerianRecord(id);
            String json = objectMapper.writeValueAsString(keplerianRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getPtolemaicRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPtolemaicRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PtolemaicRecord ptolemaicRecord = orbitModelsService.getPtolemaicRecord(id);
            String json = objectMapper.writeValueAsString(ptolemaicRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getTruthDataRecord/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTruthDataRecord(@PathVariable @NotNull Long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TruthDataRecord truthDataRecord = orbitModelsService.getTruthDataRecord(id);
            String json = objectMapper.writeValueAsString(truthDataRecord);
            if (StringUtils.isNullOrEmpty(json) || "null".equalsIgnoreCase(json)) {
                return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Object>(json, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
