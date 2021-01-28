package com.forexApp.service.commands;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.CommandsRepository;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private final ForexRepository forexRepository = new ForexRepository();
    private final CommandsRepository commandsRepository = new CommandsRepository();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    public boolean isValidateDate(String inputDateString) {
        inputDateString.replaceAll("[.,/']", "-");
        try {
            parseOnLocalDate(inputDateString);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid date format: " + inputDateString + ". Required format yyyy.MM.dd");
            return false;
        }
    }

    public boolean validateTime(String inputTimeString) {
        try {
            parseOnLocalTime(inputTimeString);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time format: " + inputTimeString);
            return false;
        }
    }

    public LocalTime parseOnLocalTime(String stringTime) {
        if (stringTime.length() == 2) {
            stringTime += ":00";
        }
        return LocalTime.parse(stringTime, timeFormatter);
    }

    public LocalDate parseOnLocalDate(String stringDate) {
        return LocalDate.parse(stringDate, dateFormatter);
    }

    public void saveResult(BigDecimal result) {
        commandsRepository.getCommandsList().add(result.toString());
    }

    public void saveResult(String result) {
        commandsRepository.getCommandsList().add(result);
    }

    public void wrongCommand() {
        commandsRepository.getCommandsList().add("wrong command");
        System.out.println("wrong command");
    }

    public void printAndSaveEachRecord(List<ForexRecord> list) {
        for (ForexRecord forexRecord : list) {
            commandsRepository.getCommandsList().add(forexRecord.toString());
            System.out.println(forexRecord.toString());
        }
    }

    public List<ForexRecord> filterByDate(String dateString) {
        LocalDate date = parseOnLocalDate(dateString);
        return forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<ForexRecord> filterByDate(LocalDate date) {
        return forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public List<ForexRecord> filterByTime(String dateString, String timeString) {
        LocalTime time = parseOnLocalTime(timeString);
        LocalDate date = parseOnLocalDate(dateString);
        return forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .collect(Collectors.toList());
    }

    public List<ForexRecord> filterByTime(LocalDate date, LocalTime time) {
        return forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .collect(Collectors.toList());
    }

    public boolean IsContainDateAtIndexOne(String secondWord) {
        if (secondWord.matches("^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$")) {
            LocalDate date = parseOnLocalDate(secondWord);
            return true;
        } else {
            return false;
        }
    }
}
