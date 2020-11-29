package com.infoshare;

public class WeightClassifier {

    public WeightType classify(int weight) {
        if (weight <= 50) {
            return WeightType.LIGHT;
        } else if (weight < 100) {
            return WeightType.MEDIUM;
        } else {
            return WeightType.HEAVY;
        }
    }


}
