package com.forexApp.app;

import com.forexApp.io.DataReader;

import java.io.*;

public class AppControl {

    File file = new File(DataReader.FILE_NAME);
    FileReader fileReader;

    {
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    BufferedReader br = new BufferedReader(fileReader);

    public String getHigh(String date) throws IOException {
        return br.readLine();
    }

}
