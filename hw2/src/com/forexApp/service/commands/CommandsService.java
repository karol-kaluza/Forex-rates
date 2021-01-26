package com.forexApp.service.commands;

import com.forexApp.repository.CommandsRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommandsService {

    private final CommandsRepository commandsRepository = new CommandsRepository();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    public boolean isValidateDate(String inputDateString) {
        inputDateString.replaceAll("[.,/']", "-");
        try {
            parseOnLocalDate(inputDateString);
            return true;
        } catch (DateTimeParseException | NullPointerException e) { //TODO find correct Exception
            System.out.println("Invalid date format: " + inputDateString + ". Required format yyyy.MM.dd");
            return false;
        }
    }

    private void validateTime(String inputTimeString) {
        try {
            parseOnLocalTime(inputTimeString);
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time format: " + inputTimeString);
        }
    }

//    public void validateDate(String stringDate) {
//        /*TODO sprawdzić lepszą metodę
//        TODO walidacja daty całkiem na początku przy recognizeGetType()
//        */
//        if (stringDate.equals("\\d{4}\\.\\d{2}\\.\\d{2}")) {
//            parseOnLocalDate(stringDate);
//        }else {
//            wrongCommand();
//        }

    public LocalTime parseOnLocalTime(String stringTime){
        return LocalTime.parse(stringTime, timeFormatter);
    }

    public LocalDate parseOnLocalDate(String stringDate) {
        return LocalDate.parse(stringDate, dateFormatter);
    }

    public void saveResult(BigDecimal result) {
        commandsRepository.getCommandsList().add(result.toString());
    }

    public void wrongCommand() {
        commandsRepository.getCommandsList().add("wrong command");
        System.out.println("wrong command");
    }
}
