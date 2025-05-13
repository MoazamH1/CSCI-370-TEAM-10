package com.diabexpert.controller;

import com.diabexpert.dto.SurveyRequest;
import com.diabexpert.dto.PredictionResponse;
import com.diabexpert.service.SmileModelLoader;
import org.springframework.web.bind.annotation.*;
import smile.classification.RandomForest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/predict")
public class PredictionController {

    private final RandomForest model;

    public PredictionController() {
        this.model = SmileModelLoader.loadTrainedModel("src/main/resources/diabetes_prediction_dataset.csv");
    }

    @PostMapping
    public PredictionResponse predict(@RequestBody SurveyRequest request) {
        double[] input = new double[]{
            mapGender(request.gender),
            request.age,
            request.hypertension,
            request.heart_disease,
            mapSmoking(request.smoking),
            request.bmi,
            request.hba1c,
            request.glucose
        };

        int result = model.predict(input);
        String severity = mapSeverity(result);
        return new PredictionResponse(severity);
    }

    private double mapGender(String g) {
        return switch (g.toLowerCase().trim()) {
            case "male" -> 1;
            case "female" -> 0;
            default -> 2;
        };
    }

    private double mapSmoking(String s) {
        return switch (s.toLowerCase().trim()) {
            case "never" -> 0;
            case "no info" -> 1;
            case "current" -> 2;
            case "former" -> 3;
            case "not current" -> 4;
            case "ever" -> 5;
            default -> 1;
        };
    }

    private String mapSeverity(int label) {
        return switch (label) {
            case 0 -> "Low Risk";
            case 1 -> "Moderate Risk";
            case 2 -> "High Risk";
            case 3 -> "Critical Risk";
            default -> "Unknown";
        };
    }
}