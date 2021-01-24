package com.forexApp.service;

import com.forexApp.io.DataReader;
import com.forexApp.io.FileCreator;
import com.forexApp.io.Printer;

import java.io.IOException;
import java.util.Scanner;

public class AppControl {

    private final DataReader dataReader = new DataReader();
    private final Printer printer = new Printer();
    private final Scanner sc = new Scanner(System.in);
    private final FileCreator fileCreator = new FileCreator();

    public void start() throws IOException {
        fileCreator.createLogDirectory();
        fileCreator.createLogFile();
        String userInput = "";
        dataReader.readFileName();
        System.out.println("File exist! Now, type command: ");
        printer.showMainMenu();
        while (!userInput.equals("exit")) {
            System.out.println("Type command:");
            userInput = sc.nextLine();
            dataReader.readUsersCommand(userInput);
        }
    }
}
