package com.infoshare;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    public WeightType classifyByProperties(Integer weight) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream("weight.properties");
        properties.load(input);
        int lightLimit = Integer.parseInt(properties.getProperty("lightLimit"));
        int mediumLimit = Integer.parseInt(properties.getProperty("mediumLimit"));
        if (weight <= lightLimit) {
            return WeightType.LIGHT;
        } else if (weight < mediumLimit) {
            return WeightType.MEDIUM;
        } else {
            return WeightType.HEAVY;
        }
    }
}

