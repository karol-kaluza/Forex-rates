package com.forexApp.app;

import com.forexApp.io.DataReader;

import java.io.IOException;

public class ForexApp {
    public static void main(String[] args) throws IOException {
        DataReader dataReader = new DataReader();
        dataReader.readFileName();

    }

}
