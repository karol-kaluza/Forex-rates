package com.forexApp;

import com.forexApp.service.AppService;

import java.io.IOException;

public class ForexApp {
    public static void main(String[] args) throws IOException {
        AppService appControl = new AppService();
        appControl.start();


//        if("2020.11.05".matches("^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$")){
//            System.out.println("zgadza się");
//        }else {
//            System.out.println("nie działa");
//        }
    }
}
// ((?:19|20)[0-9][0-9])\\.(0?[1-9]|1[012])\\.(0?[1-9]|[12][0-9]|3[01])$
// ^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$
