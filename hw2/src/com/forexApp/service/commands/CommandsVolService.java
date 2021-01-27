package com.forexApp.service.commands;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandsVolService {

    private final CommandsService commandsService = new CommandsService();
    private final ForexRepository forexRepository = new ForexRepository();
    private final CommandsGetService commandsGet = new CommandsGetService();

    public void recognizeVolType(String[] userCommand) {
        switch (userCommand.length) {
            case 2 -> {
                if (commandsService.isValidateDate(userCommand[1])) {
                    System.out.println(volatilityByDate(userCommand));
                } else {
                    commandsService.wrongCommand();
                }
            }
            case 3 -> {
                if (commandsService.isValidateDate(userCommand[1])) {
                    if (commandsService.validateTime(userCommand[2])) {
                        System.out.println(volatilityByTime(userCommand));
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

    private BigDecimal volatilityByDate(String[] usersCommand) {
        LocalDate date = commandsService.parseOnLocalDate(usersCommand[1]);
        BigDecimal volatilityByDay = commandsGet.getHighByDay(forexRepository.getForexRecordsList(), date)
                .subtract(commandsGet.getLowByDay(forexRepository.getForexRecordsList(), date));
        commandsService.saveResult(volatilityByDay);
        return volatilityByDay;
    }

    private BigDecimal volatilityByTime(String[] usersCommand) {
        LocalDate date = commandsService.parseOnLocalDate(usersCommand[1]);
        LocalTime time = commandsService.parseOnLocalTime(usersCommand[2]);
        BigDecimal volatilityByTime = commandsGet.getHighByTime(forexRepository.getForexRecordsList(), date, time)
                .subtract(commandsGet.getLowByTime(forexRepository.getForexRecordsList(), date, time));
        commandsService.saveResult(volatilityByTime);
        return volatilityByTime;
    }

    public LocalDate mostVolatileDay() {
        ForexRecord forexRecord = Collections.max(forexRepository.getForexRecordsList(),
                Comparator.comparing(x -> x.getHigh().subtract(x.getLow())));
        LocalDate maxVolatileDay = forexRecord.getDate();
        commandsService.saveResult(maxVolatileDay.toString());
        return maxVolatileDay;
    }

    public String mostVolatileHour() {
        Map<String, List<ForexRecord>> rangeOneHours = forexRepository.getForexRecordsList().stream()
                .collect(Collectors.groupingBy(x -> x.getDate().toString() + " " + x.getTime().getHour()));
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal temp;
        String maxVolatileHour = null;
        for (Map.Entry<String, List<ForexRecord>> listEntry : rangeOneHours.entrySet()) {
            temp = listEntry.getValue().stream()
                    .map(x -> x.getHigh().subtract(x.getLow()))
                    .max(Comparator.naturalOrder())
                    .orElseThrow();
            if (temp.compareTo(max) > 0) {
                max = temp;
                maxVolatileHour = listEntry.getKey();
            }
        }
        commandsService.saveResult(maxVolatileHour);
        return maxVolatileHour;
    }

    public BigDecimal averageMinutelyVolatility() {
        RoundingMode rounding = RoundingMode.HALF_UP;
        BigDecimal sum = forexRepository.getForexRecordsList().stream()
                .map(x -> x.getHigh().subtract(x.getLow()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgMin = sum.divide(new BigDecimal(forexRepository.getForexRecordsList().size()), rounding);
        commandsService.saveResult(avgMin);
        return avgMin;
    }

    public BigDecimal averageHourlyVolatility() {
        RoundingMode rounding = RoundingMode.HALF_UP;
        BigDecimal sum = BigDecimal.ZERO;
        Map<String, List<ForexRecord>> rangeDaily = forexRepository.getForexRecordsList().stream()
                .collect(Collectors.groupingBy(x -> x.getDate().toString() + " " + x.getTime().getHour()));
        for (Map.Entry<String, List<ForexRecord>> listEntry : rangeDaily.entrySet()) {
            sum = listEntry.getValue().stream()
                    .map(x -> x.getHigh().subtract(x.getLow()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        BigDecimal avgHourly = sum.divide(new BigDecimal(forexRepository.getForexRecordsList().size()), rounding);
        commandsService.saveResult(avgHourly);
        return avgHourly;
    }

    public BigDecimal averageDailyVolatility() {
        RoundingMode rounding = RoundingMode.HALF_UP;
        BigDecimal sum = BigDecimal.ZERO;
        Map<String, List<ForexRecord>> rangeDaily = forexRepository.getForexRecordsList().stream()
                .collect(Collectors.groupingBy(x -> x.getDate().toString()));
        for (Map.Entry<String, List<ForexRecord>> listEntry : rangeDaily.entrySet()) {
            sum = listEntry.getValue().stream()
                    .map(x -> x.getHigh().subtract(x.getLow()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        BigDecimal avgDaily = sum.divide(new BigDecimal(forexRepository.getForexRecordsList().size()), rounding);
        commandsService.saveResult(avgDaily);
        return avgDaily;
    }
}

