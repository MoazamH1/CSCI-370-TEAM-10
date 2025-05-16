package com.example.diabexpert;

import java.util.*;

public class RecommendationEngine {
    public static List<String> generate(double bmi, double glucose) {
        List<String> recs = new ArrayList<>();
        if (bmi > 30) recs.add("Increase physical activity");
        if (glucose > 125) recs.add("Reduce sugar intake");
        if (recs.isEmpty()) recs.add("Keep up the healthy habits!");
        return recs;
    }
}
