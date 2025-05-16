package com.example.diabexpert;

import java.util.*;

public class DecisionTree {
    public DecisionTreeNode root;

    public void train(List<UserData> dataset) {
        root = buildTree(dataset);
    }

    private DecisionTreeNode buildTree(List<UserData> data) {
        if (isPure(data)) {
            return new DecisionTreeNode(data.get(0).label);
        }

        int bestFeature = 0;
        double bestThreshold = data.get(0).features[0];
        double bestGini = Double.MAX_VALUE;
        List<UserData> leftBest = new ArrayList<>();
        List<UserData> rightBest = new ArrayList<>();

        for (int i = 0; i < data.get(0).features.length; i++) {
            double threshold = getMedian(data, i);
            List<UserData> left = new ArrayList<>();
            List<UserData> right = new ArrayList<>();

            for (UserData d : data) {
                if (d.features[i] <= threshold) left.add(d);
                else right.add(d);
            }

            double gini = giniImpurity(left, right);
            if (gini < bestGini) {
                bestGini = gini;
                bestFeature = i;
                bestThreshold = threshold;
                leftBest = left;
                rightBest = right;
            }
        }

        DecisionTreeNode node = new DecisionTreeNode(bestFeature, bestThreshold);
        node.left = buildTree(leftBest);
        node.right = buildTree(rightBest);
        return node;
    }

    private boolean isPure(List<UserData> data) {
        int label = data.get(0).label;
        for (UserData d : data) {
            if (d.label != label) return false;
        }
        return true;
    }

    private double getMedian(List<UserData> data, int featureIndex) {
        return data.stream()
                   .mapToDouble(d -> d.features[featureIndex])
                   .sorted()
                   .skip(data.size() / 2)
                   .findFirst().orElse(0);
    }

    private double giniImpurity(List<UserData> left, List<UserData> right) {
        int total = left.size() + right.size();
        return (left.size() * gini(left) + right.size() * gini(right)) / total;
    }

    private double gini(List<UserData> data) {
        int[] counts = new int[2];
        for (UserData d : data) counts[d.label]++;
        double p0 = counts[0] / (double) data.size();
        double p1 = counts[1] / (double) data.size();
        return 1 - (p0 * p0 + p1 * p1);
    }

    public int predict(double[] features) {
        DecisionTreeNode node = root;
        while (!node.isLeaf()) {
            node = (features[node.featureIndex] <= node.threshold) ? node.left : node.right;
        }
        return node.classification;
    }
}
