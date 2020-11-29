package com.infoshare;

import java.io.FileInputStream;
import java.io.IOException;
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
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String weightPropertiesPath = rootPath + "weight.properties";
        Properties weightProps = new Properties();
        weightProps.load(new FileInputStream(weightPropertiesPath));
        int lightLimit = Integer.parseInt(weightProps.getProperty("lightLimit"));
        int mediumLimit = Integer.parseInt(weightProps.getProperty("mediumLimit"));
        if (weight <= lightLimit) {
            return WeightType.LIGHT;
        } else if (weight < mediumLimit) {
            return WeightType.MEDIUM;
        } else {
            return WeightType.HEAVY;
        }
    }
}

