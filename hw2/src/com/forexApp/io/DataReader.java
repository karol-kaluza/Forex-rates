package com.forexApp.io;

import com.forexApp.commands.Commands;
import com.forexApp.model.ForexRecord;
import com.forexApp.service.ForexService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class DataReader {

    public static final String FILE_NAME = "DAT_MT_EURUSD_M1_202011.csv";
    private final Scanner scanner = new Scanner(System.in);
    private final ForexService forexService = new ForexService();
    private final Printer printer = new Printer();
    private final Commands commands = new Commands();
    private BufferedReader br;
    private String userInput;

    public void readFileName() throws IOException {
        boolean isExist;
        System.out.println("Type a filename: ");
        userInput = scanner.nextLine();
        do {
            if (userInput.equals(FILE_NAME)) {
                readFile();
                isExist = true;
            } else {
                tryAgain();
                userInput = scanner.nextLine();
                isExist = false;
            }
        } while (!isExist);
    }

    private void readFile() throws IOException {
        br = new BufferedReader(new FileReader(FILE_NAME));
        br.readLine();
        readContentFormFile();
    }

    private void readContentFormFile() throws IOException {
        String singleLine;
        while ((singleLine = br.readLine()) != null) {
            String[] content = singleLine.split(",");
            ForexRecord forexRecord = forexService.createForexRecord(content);
            forexService.getForexRecordList().add(forexRecord);
        }
    }

    public void tryAgain() {
        System.out.println("File: " + userInput + " doesn't exist. \n" +
                "Existing file: \"DAT_MT_EURUSD_M1_202011.csv\". Type filename again: ");
    }

    public void getUserChoice() {
        String userInput = scanner.nextLine().toLowerCase();
        if (userInput.substring(0, 8).equals("get high")) {
            System.out.println("you typed get high");
            LocalDate date = forexService.convertToLocalDate(userInput.substring(9, 19));
            System.out.println("datum: " + date);
            commands.getHighByDay(forexService.getForexRecordList(), date);

        } else {
            System.out.println("you put something different than get high 2020.11.05");
            System.out.println(userInput.substring(0, 8));
        }
    }

    public void readUsersCommand(String userInput) {
        String[] userInputArray = userInput.split("\\s+");
        switch (userInputArray[0]) {
            case "get" -> commands.manageGet(userInputArray);
            case "volatility" -> commands.manageVolatility(userInputArray);
            case "most_volatile_day" -> commands.mostVolatileDay();
            case "most_volatile_hour" -> commands.mostVolatileHour();
            case "average_minutely_volatility" -> commands.averageMinutelyVolatility();
            case "average_hourly_volatility" -> commands.averageHourlyVolatility();
            case "average_daily_volatility" -> commands.averageDailyVolatility();
            case "commands" -> printer.showCommandsList();
            case "example" -> printer.showExampleCommands();
            case "exit" -> printer.exitCommand();
            case "test" -> forexService.getForexRecordList().stream().limit(3).forEach(System.out::println);
            default -> printer.showWarningWrongCommand();
        }
    }
}




