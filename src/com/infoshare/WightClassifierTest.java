package com.infoshare;

import java.util.Scanner;

public class WightClassifierTest {
    public static void main(String[] args) {
        WeightClassifier classifier = new WeightClassifier();
        System.out.println("What is your weight? ");
        Scanner scanner = new Scanner(System.in);
        int userWeight = scanner.nextInt();
        System.out.println("Your weight is: " + classifier.classify(userWeight) + classifier.classify(userWeight).getDescription());
    }
}
