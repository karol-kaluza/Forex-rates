package com.forexApp.io;

import com.forexApp.service.commands.CommandsGetService;
import com.forexApp.model.ForexRecord;
import com.forexApp.repository.ForexRepository;
import com.forexApp.service.ForexService;
import com.forexApp.service.commands.CommandsVolService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DataReader {

    public static final String FILE_NAME = "DAT_MT_EURUSD_M1_202011.csv";

    private final CommandsVolService commandsVolService = new CommandsVolService();
    private final CommandsGetService commandsGetService = new CommandsGetService();
    private final ForexRepository forexRepository = new ForexRepository();
    private final ForexService forexService = new ForexService();
    private final Scanner scanner = new Scanner(System.in);
    private final Printer printer = new Printer();
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
            case "get" -> commandsGetService.recognizeGetType(userInputArray);
            case "volatility" -> commandsVolService.mostVolatileDay();
            case "most_volatile_hour" -> commandsVolService.mostVolatileHour();
            case "average_minutely_volatility" -> commandsVolService.averageMinutelyVolatility();
            case "average_hourly_volatility" -> commandsVolService.averageHourlyVolatility();
            case "average_daily_volatility" -> commandsVolService.averageDailyVolatility();
            case "commands" -> printer.showCommandsList();
            case "example" -> printer.showExampleCommands();
            case "exit" -> printer.exitCommand();
            default -> printer.showWarningWrongCommand();
        }
    }

    public void tryAgain() {
        System.out.println("File: " + userInput + " doesn't exist. \n" +
                "Existing file: \"DAT_MT_EURUSD_M1_202011.csv\". Type filename again: ");
    }
}




