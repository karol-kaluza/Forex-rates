package com.forexApp;

import com.forexApp.service.AppControl;

import java.io.IOException;

public class ForexApp {
    public static void main(String[] args) throws IOException {
        AppControl appControl = new AppControl();
        appControl.start();
    }
}
