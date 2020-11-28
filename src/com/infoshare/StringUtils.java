package com.infoshare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringUtils {

    public static final String FILE_NAME = "testFile.txt";
    int counterLines = 0;
    String[] lines;

    File file = new File(FILE_NAME);
    Scanner scanFile = new Scanner(file);

    public StringUtils() throws FileNotFoundException {
    }

    public int countLinesFromFile(String fileName) {
        while (scanFile.hasNextLine()) {
            scanFile.nextLine();
            counterLines++;
        }
        return counterLines;
    }

    public String[] getLines(String yourLongText) {
        String[] lines = yourLongText.split(",");
        return lines;
    }

}
