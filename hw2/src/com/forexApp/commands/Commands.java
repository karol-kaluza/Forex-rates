package com.forexApp.commands;

import com.forexApp.io.Printer;
import com.forexApp.model.ForexRecord;
import com.forexApp.service.ForexService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

public class Commands {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Printer printer = new Printer();
    private final ForexService forexService = new ForexService();


    public void manageGet(String[] usersCommand) {
            LocalDate date = LocalDate.parse(usersCommand[2].replaceAll("[.,/']","-"));
        switch (usersCommand[1]) {
            case "open" -> System.out.println("xxx get open " + date);
            case "high" -> getHighByDay(forexService.getForexRecordList(),date);
            case "low" -> System.out.println("xxx get low "+ date);
            case "close" -> System.out.println("xxx get close "+ date);
            //TODO
            default -> System.out.println("sprawdzić czy jest data");
        }
    }

    public void getHighByDay(List<ForexRecord> list, LocalDate date) {
        System.out.println("method getHigh is working");
        list.stream()
                .limit(3)
                .forEach(System.out::println);
//                .map(ForexRecord::getHigh)
//                .max(Comparator.naturalOrder());
//        return max;
    }

    private void checkDateAndTime(String[] usersCommand) {
        String thirdWord = usersCommand[2].replaceAll("[.,/']","-");
        if (isValidDate(thirdWord) && usersCommand.length == 3){
            getHighByDay(forexService.getForexRecordList(), forexService.convertToLocalDate(usersCommand[2]));
        }else if (isValidDate(thirdWord) && usersCommand.length >= 3){

        }
    }

    private boolean isValidTime() {
        //TODO
        return false;
    }

    private boolean isValidDate(String singleWord) {
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(singleWord.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
//TODO
    public void mostVolatileHour() {
        System.out.println("XXX mostvoltailehour");
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
}
