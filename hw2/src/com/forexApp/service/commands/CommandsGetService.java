package com.forexApp.service.commands;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

public class CommandsGetService {

    private final ForexRepository forexRepository = new ForexRepository();
    private final Utils utils = new Utils();

    public void recognizeGetType(String[] userCommand) {
        switch (userCommand.length) {
            case 2 -> {
                if (utils.isValidateDate(userCommand[1])) {
                    getAllResultsByDay(userCommand);
                } else {
                    utils.wrongCommand();
                }
            }
            case 3 -> getResultByDay(userCommand);
            case 4 -> {
                if (utils.isValidateDate(userCommand[2])) {
                    if (utils.validateTime(userCommand[3])) {
                        getResultByTime(userCommand);
                    } else {
                        utils.wrongCommand();
                    }
                } else {
                    utils.wrongCommand();
                }
            }
            default -> utils.wrongCommand();
        }
    }

    public void getResultByDay(String[] usersCommand) {
        LocalDate date;
        if (utils.IsContainDateAtIndexOne(usersCommand[1])) {
            getAllResultsByTime(usersCommand);
        } else {
            if (utils.isValidateDate(usersCommand[2])) {
                date = utils.parseOnLocalDate(usersCommand[2]);
                switch (usersCommand[1]) {
                    case "open" -> System.out.println(getOpenByDay(forexRepository.getForexRecordsList(), date));
                    case "high" -> System.out.println(getHighByDay(forexRepository.getForexRecordsList(), date));
                    case "low" -> System.out.println(getLowByDay(forexRepository.getForexRecordsList(), date));
                    case "close" -> System.out.println(getCloseByDay(forexRepository.getForexRecordsList(), date));
                    default -> utils.wrongCommand();
                }
            } else {
                utils.wrongCommand();
            }
        }
    }

    public void getAllResultsByDay(String[] usersCommand) {
        List<ForexRecord> forexRecords = utils.filterByDate(usersCommand[1]);
        utils.printAndSaveEachRecord(forexRecords);
    }

    public void getAllResultsByTime(String[] usersCommand) {
        List<ForexRecord> forexRecords = utils.filterByTime(usersCommand[1], usersCommand[2]);
        utils.printAndSaveEachRecord(forexRecords);
    }

    public BigDecimal getOpenByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal open = utils.filterByDate(date).stream()
                .findFirst()
                .map(ForexRecord::getOpen)
                .orElseThrow();
        utils.saveResult(open);
        return open;
    }

    public BigDecimal getHighByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal high = utils.filterByDate(date).stream()
                .map(ForexRecord::getHigh)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        utils.saveResult(high);
        return high;
    }

    public BigDecimal getLowByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal low = utils.filterByDate(date).stream()
                .map(ForexRecord::getLow)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        utils.saveResult(low);
        return low;
    }

    public BigDecimal getCloseByDay(List<ForexRecord> list, LocalDate date) {
        long count = utils.filterByDate(date).stream().count();
        BigDecimal close = utils.filterByDate(date).stream()
                .skip(count - 1)
                .findFirst()
                .map(ForexRecord::getClose)
                .orElseThrow();
        utils.saveResult(close);
        return close;
    }

    public void getResultByTime(String[] usersCommand) {
        LocalDate date = utils.parseOnLocalDate(usersCommand[2]);
        LocalTime time = utils.parseOnLocalTime(usersCommand[3]);
        switch (usersCommand[1]) {
            case "open" -> System.out.println(getOpenByTime(forexRepository.getForexRecordsList(), date, time));
            case "high" -> System.out.println(getHighByTime(forexRepository.getForexRecordsList(), date, time));
            case "low" -> System.out.println(getLowByTime(forexRepository.getForexRecordsList(), date, time));
            case "close" -> System.out.println(getCloseByTime(forexRepository.getForexRecordsList(), date, time));
            default -> utils.wrongCommand();
        }
    }

    public BigDecimal getOpenByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal open = utils.filterByTime(date, time).stream()
                .findFirst()
                .map(ForexRecord::getOpen)
                .orElseThrow();
        utils.saveResult(open);
        return open;
    }

    public BigDecimal getHighByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal high = utils.filterByTime(date, time).stream()
                .map(ForexRecord::getHigh)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        utils.saveResult(high);
        return high;
    }

    public BigDecimal getLowByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal low = utils.filterByTime(date, time).stream()
                .map(ForexRecord::getLow)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        utils.saveResult(low);
        return low;
    }

    public BigDecimal getCloseByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        long count = utils.filterByTime(date, time).stream().count();
        BigDecimal close = utils.filterByTime(date, time).stream()
                .skip(count - 1)
                .findFirst()
                .map(ForexRecord::getClose)
                .orElseThrow();
        utils.saveResult(close);
        return close;
    }
}
