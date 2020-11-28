package com.infoshare;

import java.io.FileNotFoundException;


public class StringTester {
    public static void main(String[] args) throws FileNotFoundException {

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

        System.out.println("counter lines: " + stringUtils.countLinesFromFile(StringUtils.FILE_NAME));
        String [] linesABC = stringUtils.getLines(fullText);
        System.out.println("first line: " + linesABC[0]);

    }
}
