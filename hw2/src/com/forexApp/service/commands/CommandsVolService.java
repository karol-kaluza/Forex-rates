package com.forexApp.service.commands;

import com.forexApp.model.ForexRecord;
import com.forexApp.repository.ForexRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandsVolService {

    private final Utils utils = new Utils();
    private final ForexRepository forexRepository = new ForexRepository();
    private final CommandsGetService commandsGet = new CommandsGetService();

    public void recognizeVolType(String[] userCommand) {
        switch (userCommand.length) {
            case 2 -> {
                if (utils.isValidateDate(userCommand[1])) {
                    System.out.println(volatilityByDate(userCommand));
                } else {
                    utils.wrongCommand();
                }
            }
            case 3 -> {
                if (utils.isValidateDate(userCommand[1])) {
                    if (utils.validateTime(userCommand[2])) {
                        System.out.println(volatilityByTime(userCommand));
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

    private BigDecimal volatilityByDate(String[] usersCommand) {
        LocalDate date = utils.parseOnLocalDate(usersCommand[1]);
        BigDecimal volatilityByDay = commandsGet.getHighByDay(forexRepository.getForexRecordsList(), date)
                .subtract(commandsGet.getLowByDay(forexRepository.getForexRecordsList(), date));
        utils.saveResult(volatilityByDay);
        return volatilityByDay;
    }

    private BigDecimal volatilityByTime(String[] usersCommand) {
        LocalDate date = utils.parseOnLocalDate(usersCommand[1]);
        LocalTime time = utils.parseOnLocalTime(usersCommand[2]);
        BigDecimal volatilityByTime = commandsGet.getHighByTime(forexRepository.getForexRecordsList(), date, time)
                .subtract(commandsGet.getLowByTime(forexRepository.getForexRecordsList(), date, time));
        utils.saveResult(volatilityByTime);
        return volatilityByTime;
    }

    public LocalDate mostVolatileDay() {
        ForexRecord forexRecord = Collections.max(forexRepository.getForexRecordsList(),
                Comparator.comparing(x -> x.getHigh().subtract(x.getLow())));
        LocalDate maxVolatileDay = forexRecord.getDate();
        utils.saveResult(maxVolatileDay.toString());
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
        utils.saveResult(maxVolatileHour);
        return maxVolatileHour;
    }

    public BigDecimal averageDailyVolatility() {
        Map<String, List<ForexRecord>> rangeDaily = forexRepository.getForexRecordsList().stream()
                .collect(Collectors.groupingBy(x -> x.getDate().toString()));
        BigDecimal avgDaily = calculateAverage(rangeDaily);
        utils.saveResult(avgDaily);
        return avgDaily;
    }

    public BigDecimal averageHourlyVolatility() {
        Map<String, List<ForexRecord>> rangeHourly = forexRepository.getForexRecordsList().stream()
                .collect(Collectors.groupingBy(x -> x.getDate().toString() + " " + x.getTime().getHour()));
        BigDecimal avgHourly = calculateAverage(rangeHourly);
        utils.saveResult(avgHourly);
        return avgHourly;
    }

    public BigDecimal averageMinutelyVolatility() {
        RoundingMode rounding = RoundingMode.HALF_UP;
        BigDecimal sum = forexRepository.getForexRecordsList().stream()
                .map(x -> x.getHigh().subtract(x.getLow()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgMin = sum.divide(new BigDecimal(forexRepository.getForexRecordsList().size()), rounding);
        utils.saveResult(avgMin);
        return avgMin;
    }

    private BigDecimal calculateAverage(Map<String, List<ForexRecord>> rangeMap) {
        RoundingMode rounding = RoundingMode.HALF_UP;
        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<String, List<ForexRecord>> listEntry : rangeMap.entrySet()) {
            sum = listEntry.getValue().stream()
                    .map(x -> x.getHigh().subtract(x.getLow()))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        BigDecimal avg = sum.divide(new BigDecimal(forexRepository.getForexRecordsList().size()), rounding);
        return avg;
    }
}

