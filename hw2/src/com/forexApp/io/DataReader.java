package com.forexApp.io;

import com.forexApp.service.CommandsService;
import com.forexApp.model.ForexRecord;
import com.forexApp.repository.ForexRepository;
import com.forexApp.service.ForexService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DataReader {

    public static final String FILE_NAME = "DAT_MT_EURUSD_M1_202011.csv";
    private final Scanner scanner = new Scanner(System.in);
    private final ForexService forexService = new ForexService();
    private final ForexRepository forexRepository = new ForexRepository();
    private final Printer printer = new Printer();
    private final CommandsService commandsService = new CommandsService();
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
            forexRepository.getForexRecordsList().add(forexRecord);
        }
    }

    public void readUsersCommand(String userInput) {
        String[] userInputArray = userInput.split("\\s+");
        switch (userInputArray[0]) {
            case "get" -> commandsService.manageGet(userInputArray);
            case "volatility" -> commandsService.manageVolatility(userInputArray);
            case "most_volatile_day" -> commandsService.mostVolatileDay();
            case "most_volatile_hour" -> commandsService.mostVolatileHour();
            case "average_minutely_volatility" -> commandsService.averageMinutelyVolatility();
            case "average_hourly_volatility" -> commandsService.averageHourlyVolatility();
            case "average_daily_volatility" -> commandsService.averageDailyVolatility();
            case "commands" -> printer.showCommandsList();
            case "example" -> printer.showExampleCommands();
            case "exit" -> printer.exitCommand();
            case "test" -> forexRepository.getForexRecordsList().stream().limit(3).forEach(System.out::println);
            default -> printer.showWarningWrongCommand();
        }
    }

    public void tryAgain() {
        System.out.println("File: " + userInput + " doesn't exist. \n" +
                "Existing file: \"DAT_MT_EURUSD_M1_202011.csv\". Type filename again: ");
    }
}




