package com.diabexpert.service;

import smile.classification.RandomForest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SmileModelLoader {

    public static RandomForest loadTrainedModel(String csvPath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvPath));
            reader.readLine(); // skip header
            String line;

            List<double[]> xList = new ArrayList<>();
            List<Integer> yList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length < 9) continue;

                double gender = mapGender(tokens[0]);
                double age = Double.parseDouble(tokens[1]);
                double hypertension = Double.parseDouble(tokens[2]);
                double heartDisease = Double.parseDouble(tokens[3]);
                double smoking = mapSmoking(tokens[4]);
                double bmi = Double.parseDouble(tokens[5]);
                double hba1c = Double.parseDouble(tokens[6]);
                double glucose = Double.parseDouble(tokens[7]);
                int label = Integer.parseInt(tokens[8]);

                xList.add(new double[]{gender, age, hypertension, heartDisease, smoking, bmi, hba1c, glucose});
                yList.add(label);
            }

            reader.close();

            double[][] x = xList.toArray(new double[0][]);
            int[] y = yList.stream().mapToInt(i -> i).toArray();

            return RandomForest.fit(x, y, 100);

        } catch (Exception e) {
            throw new RuntimeException("Error loading and training model: " + e.getMessage(), e);
        }
    }

    private static double mapGender(String g) {
        return switch (g.trim().toLowerCase()) {
            case "male" -> 1;
            case "female" -> 0;
            default -> 2;
        };
    }

    private static double mapSmoking(String s) {
        return switch (s.trim().toLowerCase()) {
            case "never" -> 0;
            case "no info" -> 1;
            case "current" -> 2;
            case "former" -> 3;
            case "not current" -> 4;
            case "ever" -> 5;
            default -> 1;
        };
    }
}