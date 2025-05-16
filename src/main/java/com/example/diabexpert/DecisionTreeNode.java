package com.example.diabexpert;

public class DecisionTreeNode {
    public int featureIndex;
    public double threshold;
    public DecisionTreeNode left;
    public DecisionTreeNode right;
    public Integer classification;

    public DecisionTreeNode(int featureIndex, double threshold) {
        this.featureIndex = featureIndex;
        this.threshold = threshold;
    }

    public DecisionTreeNode(int classification) {
        this.classification = classification;
    }

    public boolean isLeaf() {
        return classification != null;
    }
}
