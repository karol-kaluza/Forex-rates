package com.forexApp.service;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.CommandsRepository;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CommandsService {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final ForexRepository forexRepository = new ForexRepository();
    private final CommandsRepository commandsRepository = new CommandsRepository();


    public void manageGet(String[] usersCommand) {
        LocalDate date = LocalDate.parse(usersCommand[2].replaceAll("[.,/']", "-"));
        switch (usersCommand[1]) {
            case "open" -> System.out.println("xxx get open " + date);
            case "high" -> System.out.println(getHighByDay(forexRepository.getForexRecordsList(), date));
            case "low" -> System.out.println("xxx get low " + date);
            case "close" -> System.out.println("xxx get close " + date);
            //TODO
            default -> System.out.println("sprawdzić czy jest data");
        }
    }

    public BigDecimal getHighByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal max = list.stream().filter(forexRecord -> forexRecord.getDate().equals(date))
                .map(forexRecord -> forexRecord.getHigh())
                .max(Comparator.naturalOrder())
                .orElseThrow();
        saveResult(max);
        return max;
    }
    private boolean isValidTime() {
        //TODO
        return false;
    }

    private boolean isValidDate(String singleWord) {
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(singleWord.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    //TODO

    public void mostVolatileHour() {
        String answer = "2020-11-03 21:00";
        commandsRepository.getCommandsList().add("result: " + answer + "\n");
        System.out.println(answer);
    }
    public void averageMinutelyVolatility() {
        System.out.println("XXX avg m vol");
    }

    public void averageHourlyVolatility() {
        System.out.println("XXX avg H vol");
    }

    public void averageDailyVolatility() {
        System.out.println("XXX avg Daily Vol");
    }

    public void manageVolatility(String[] userInputArray) {
        System.out.println("XXX voltaility");
    }

    public void mostVolatileDay() {
        System.out.println("XXX most Vol by day");
    }

    private void saveResult (BigDecimal result){
        commandsRepository.getCommandsList().add(result.toString());
    }
}
