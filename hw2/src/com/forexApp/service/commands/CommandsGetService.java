package com.forexApp.service.commands;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.CommandsRepository;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CommandsGetService {

    private final CommandsRepository commandsRepository = new CommandsRepository();
    private final ForexRepository forexRepository = new ForexRepository();
    private final CommandsService commandsService = new CommandsService();

    public void recognizeGetType(String[] userCommand) {
        switch (userCommand.length) {
            case 2 -> {
                if (commandsService.isValidateDate(userCommand[1])) {
                    getAllByDay(userCommand);
                } else {
                    commandsService.wrongCommand();
                }
            }
            case 3 -> {
                if (commandsService.isValidateDate(userCommand[2])) {
                    getResultByDay(userCommand);
                } else {
                    commandsService.wrongCommand();
                }
            }
            case 4 -> {
                if (commandsService.isValidateDate(userCommand[2])) {
                    if (commandsService.validateTime(userCommand[3])) {
                        getResultByTime(userCommand);
                    } else {
                        commandsService.wrongCommand();
                    }
                } else {
                    commandsService.wrongCommand();
                }
            }
            default -> commandsService.wrongCommand();
        }
    }

    public void getAllByDay(String[] usersCommand) {
        LocalDate date = commandsService.parseOnLocalDate(usersCommand[1]);
        List<ForexRecord> forexRecords = forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .collect(Collectors.toList());
        for (ForexRecord forexRecord : forexRecords) {
            commandsRepository.getCommandsList().add(forexRecord.toString());
            System.out.println(forexRecord.toString());
        }
    }

    public void getResultByDay(String[] usersCommand) {
        LocalDate date = commandsService.parseOnLocalDate(usersCommand[2]);
        switch (usersCommand[1]) {
            case "open" -> System.out.println(getOpenByDay(forexRepository.getForexRecordsList(), date));
            case "high" -> System.out.println(getHighByDay(forexRepository.getForexRecordsList(), date));
            case "low" -> System.out.println(getLowByDay(forexRepository.getForexRecordsList(), date));
            case "close" -> System.out.println(getCloseByDay(forexRepository.getForexRecordsList(), date));
            default -> commandsService.wrongCommand();
        }
    }

    public BigDecimal getOpenByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal open = list.stream().
                filter(forexRecord -> forexRecord.getDate().equals(date))
                .findFirst()
                .map(ForexRecord::getOpen)
                .orElseThrow();
        commandsService.saveResult(open);
        return open;
    }

    public BigDecimal getHighByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal high = list.stream().
                filter(forexRecord -> forexRecord.getDate().equals(date))
                .map(ForexRecord::getHigh)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        commandsService.saveResult(high);
        return high;
    }

    public BigDecimal getLowByDay(List<ForexRecord> list, LocalDate date) {
        BigDecimal low = list.stream().
                filter(forexRecord -> forexRecord.getDate().equals(date))
                .map(ForexRecord::getLow)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        commandsService.saveResult(low);
        return low;
    }

    public BigDecimal getCloseByDay(List<ForexRecord> list, LocalDate date) {
        long count = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .count();
        BigDecimal close = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .skip(count - 1)
                .findFirst()
                .map(ForexRecord::getClose)
                .orElseThrow();
        commandsService.saveResult(close);
        return close;
    }

    public void getResultByTime(String[] usersCommand) {
        LocalDate date = commandsService.parseOnLocalDate(usersCommand[2]);
        LocalTime time = commandsService.parseOnLocalTime(usersCommand[3]);
        switch (usersCommand[1]) {
            case "open" -> System.out.println(getOpenByTime(forexRepository.getForexRecordsList(), date, time));
            case "high" -> System.out.println(getHighByTime(forexRepository.getForexRecordsList(), date, time));
            case "low" -> System.out.println(getLowByTime(forexRepository.getForexRecordsList(), date, time));
            case "close" -> System.out.println(getCloseByTime(forexRepository.getForexRecordsList(), date, time));
            default -> commandsService.wrongCommand();
        }
    }

    public BigDecimal getOpenByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal open = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .findFirst()
                .map(ForexRecord::getOpen)
                .orElseThrow();
        commandsService.saveResult(open);
        return open;
    }

    public BigDecimal getHighByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal high = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .map(ForexRecord::getHigh)
                .max(Comparator.naturalOrder())
                .orElseThrow();
        commandsService.saveResult(high);
        return high;
    }

    public BigDecimal getLowByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        BigDecimal low = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .map(ForexRecord::getLow)
                .min(Comparator.naturalOrder())
                .orElseThrow();
        commandsService.saveResult(low);
        return low;
    }

    public BigDecimal getCloseByTime(List<ForexRecord> list, LocalDate date, LocalTime time) {
        long count = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .count();
        BigDecimal close = list.stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .skip(count - 1)
                .findFirst()
                .map(ForexRecord::getClose)
                .orElseThrow();
        commandsService.saveResult(close);
        return close;
    }


}
