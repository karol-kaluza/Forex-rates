package com.forexApp;

import com.forexApp.service.AppService;

import java.io.IOException;

public class ForexApp {
    public static void main(String[] args) throws IOException {
        AppService app = new AppService();
        app.start();
    }
}
