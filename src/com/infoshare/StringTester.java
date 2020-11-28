package com.infoshare;

public class StringTester {
    public static void main(String[] args) {

        String fullText = "Manifesto for Agile Software Development\n" +
                "\n" +
                "\n" +
                "\n" +
                "We are uncovering better ways of developing\n" +
                "software by doing it and helping others do it.\n" +
                "Through this work we have come to value.\n" +
                "\n" +
                "Individuals and interactions over processes and tools,\n" +
                "Working software over, comprehensive documentation,\n" +
                "Customer collaboration over contract negotiation,\n" +
                "Responding to change over following a plan.\n" +
                "\n" +
                "That is, while there is value in the items on\n" +
                "the right, we value the items on the left more.";

        StringUtils stringUtils = new StringUtils();

        String[] lines = stringUtils.getLines(fullText);                    // zad.1 getLines
        System.out.println(lines[0]);

        String[] words = stringUtils.getWords(lines[5]);                    // zad.2 getWords
        System.out.println(words[8]);

        String [] wordsWithoutSpecialSings = stringUtils.replaceSign(words);  //zad. 2 a) Remove dot and semicolon
        System.out.println(wordsWithoutSpecialSings[8]);

    }
}
