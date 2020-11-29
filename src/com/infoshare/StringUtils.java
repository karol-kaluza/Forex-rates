package com.infoshare;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {

    public String[] getLines(String fullText) {
        String[] lines = fullText.split("\n");
        return lines;
    }

    public String[] getWords(String fullText) {
        String[] words = fullText.toLowerCase()
                .replaceAll("[.,]", "")
                .replaceAll("\n+", " ")
                .split(" ");
        return words;
    }

    public Map<String, Integer> getWordFrequency(String fullText) {
        Map<String, Integer> frequencyOfWords = new HashMap<>();
        String[] words = getWords(fullText);
        for (String word : words) {
            if (frequencyOfWords.containsKey(word)) {
                frequencyOfWords.put(word, frequencyOfWords.get(word) + 1);
            } else {
                frequencyOfWords.put(word, 1);
            }
        }
        return frequencyOfWords;
    }

    public String removeLetterFirstAndLast(String word) {
        return word.substring(1, word.length() - 1);
    }

    public int countLetters(String word, char letter) {
        int counter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                counter++;
            }
        }
        return counter;
    }

    public String reverseWord(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            builder.append(word.charAt(i));
        }
        return builder.toString();
    }

    public String concatenate(String firstWord, String secondWord) {
        return firstWord + " " + secondWord;
    }

    public void printEveryThirdSymbol(String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((i + 1) % 3 == 0) {
                System.out.print(word.charAt(i));
            }
        }
    }
}