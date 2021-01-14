package com.forexApp.io;

import com.forexApp.model.Factory;
import com.forexApp.model.ForexRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DataReader {

    public static final String FILE_NAME = "DAT_MT_EURUSD_M1_202011.csv";
    private final Scanner scanner = new Scanner(System.in);
    private BufferedReader br;
    private final Factory factory = new Factory();
    private final Printer printer = new Printer();

    public void readFileName() throws IOException {
        boolean isExist;
        System.out.println("enter a filename: ");
        String userInput = scanner.nextLine();
        do {
            if (userInput.equals(FILE_NAME)) {
                isExist = true;
                getCommand();
            } else {
                System.out.println("that file not exist, try again: ");
                userInput = scanner.nextLine();
                isExist = false;
            }
        } while (!isExist);
    }

    private void getCommand() throws IOException {
        printer.showMenu();
        String choice = askAboutCommand();
        switch (choice) {
            case "1": {
                String date = getDate();
                System.out.println("wyświetl datę");
            }
            break;
            case "2": {
                System.out.println("2");
            }
            break;
            case "3": {
                System.out.println("3");
            }
            break;
            case "4": {
                System.out.println("4");
            }
            break;
            case "5": {
                System.out.println("5");
            }
            break;
            default: {
                System.out.println("wrong choice");
                getCommand();
            }
        }
    }

    private String getDate() {
        System.out.println("Enter date and hour: ");
        return scanner.nextLine();
    }

    private String askAboutCommand() {
        return scanner.nextLine();
    }


    public void readFile () throws IOException {
        br = new BufferedReader(new FileReader(FILE_NAME));
        br.readLine();
        readContentFormFile();
    }

    public void readContentFormFile() throws IOException {
        String singleLine;
        while ((singleLine = br.readLine()) != null){
            String [] content = singleLine.split(",");
            ForexRecord forexRecord = factory.createForexRecord(content);
            factory.getForexRecords().add(forexRecord);
        }
    }
}
