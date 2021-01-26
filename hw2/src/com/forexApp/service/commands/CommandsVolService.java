package com.forexApp.service.commands;

public class CommandsVolService {

    private final CommandsService commandsService = new CommandsService();

    public void recognizeVolType(String[] userCommand){
        switch (userCommand.length) {
//            case 2 -> commandsVolService.valatilityByDate();
//            case 3 -> commandsVolService.recognizeTimeType();
            default -> commandsService.wrongCommand();
        }
    }
    public void mostVolatileDay() {
        System.out.println("XXX most Vol by day");
    }

    public void mostVolatileHour() {
        System.out.println("xxx mostVolHour");
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
}
