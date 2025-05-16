package com.example.diabexpert;

public class UserData {
    public double[] features;
    public int label;

    public UserData(double[] features, int label) {
        this.features = features;
        this.label = label;
    }

    public double[] getFeatures() {
        return features;
    }

    public int getLabel() {
        return label;
    }
}
