package com.example.diabexpert;

public class SeverityScale {
    public static String classify(double score, double glucose) {
        if (glucose >= 300) return "High Risk (Glucose Override)";
        if (score < 0.3) return "Low Risk";
        if (score < 0.6) return "Moderate Risk";
        return "High Risk";
    }
}
