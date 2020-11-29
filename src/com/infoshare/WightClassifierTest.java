package com.infoshare;

import java.io.IOException;
import java.util.Scanner;

public class WightClassifierTest {
    public static void main(String[] args) throws IOException {
        WeightClassifier classifier = new WeightClassifier();
        System.out.println("What is your weight? ");
        Scanner scanner = new Scanner(System.in);
        int userWeight = scanner.nextInt();
//        System.out.println("Your weight is: " + classifier.classify(userWeight) + classifier.classify(userWeight).getDescription());
        System.out.println("Your weight is: " + classifier.classifyByProperties(userWeight) +      //using method classifyByProperties
                classifier.classifyByProperties(userWeight).getDescription());
    }
}
