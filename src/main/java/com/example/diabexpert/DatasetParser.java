package com.example.diabexpert;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DatasetParser {
    public static List<UserData> parseCSV(String filepath) throws IOException {
        List<UserData> dataset = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        lines.remove(0); // Skip header

        for (String line : lines) {
            String[] tokens = line.split(",");
            double[] features = new double[tokens.length - 1];
            for (int i = 0; i < tokens.length - 1; i++) {
                try {
                    features[i] = Double.parseDouble(tokens[i]);
                } catch (NumberFormatException e) {
                    features[i] = -1; // Default for missing/invalid
                }
            }
            int label = Integer.parseInt(tokens[tokens.length - 1]);
            dataset.add(new UserData(features, label));
        }

        return dataset;
    }
}
