package com.infoshare;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
public class StringTester {
    public static void main(String[] args) throws IOException {

        String fileName = "testFile.txt";
        String fullText = Files.readString(Path.of(fileName));
        StringUtils stringUtils = new StringUtils();

        String[] lines = stringUtils.getLines(fullText);
        System.out.println("Get lines[0]: " + lines[0]);

        String[] words = stringUtils.getWords(fullText);
        System.out.println("Get words[5]: " + words[5]);

        Map<String, Integer> frequencyOfWords = stringUtils.getWordFrequency(fullText);
        String wordToCheck = "the";
        System.out.println("Number of appearance word \"" + wordToCheck + "\": " + frequencyOfWords.get(wordToCheck));

        String word = words[4];
        System.out.println("Word without first and last letter: " + stringUtils.removeLetterFirstAndLast(word));

        System.out.println("Number of appearance letter \"e\" in word \"development\": "
                + stringUtils.countLetters(word, 'e'));

        System.out.println("Reverse word \"development\": " + stringUtils.reverseWord(word));

        System.out.println("Concatenation: " + stringUtils.concatenate(word, words[0]));

        System.out.print("Every third symbol from \"development\": ");
        stringUtils.printEveryThirdSymbol(word);                                                                        
    }
}
