package com.keplerianptolemaic.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.keplerianptolemaic.model.Predictor;

public class PredictionContext {
    
    private static final int PREDICTION_EPOCH_YEAR = 2014;
    private static final int PREDICTION_EPOCH_MONTH = 4;
    private static final int PREDICTION_EPOCH_DAY = 8;

    private Predictor predictor;

    public PredictionContext(Predictor predictor) {
        this.predictor = predictor;
    }

    public double getPredictionAngle(Date date) {
        long timeId = dateToTimeId(date);
        return predictor.getMarsTheta(timeId);
    }

    private long dateToTimeId(Date date) {
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.ofInstant(date.toInstant(), ZoneId.of("UTC")), LocalTime.ofSecondOfDay(0));
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.of(PREDICTION_EPOCH_YEAR, PREDICTION_EPOCH_MONTH, PREDICTION_EPOCH_DAY), LocalTime.ofSecondOfDay(0));

        return ChronoUnit.DAYS.between(startDateTime, endDateTime);
    }
}
