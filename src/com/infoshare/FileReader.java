package com.infoshare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static final String FILE_NAME = "testFile.txt";
    int counterLines = 0;

    File file = new File(FILE_NAME);
    Scanner scanFile = new Scanner(file);

    public FileReader() throws FileNotFoundException {
    }
    public int countLinesFromFile(String fileName) {
        while (scanFile.hasNextLine()) {
            scanFile.nextLine();
            counterLines++;
        }
        return counterLines;
    }

}
