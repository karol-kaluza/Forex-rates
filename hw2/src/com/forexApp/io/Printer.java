package com.forexApp.io;

public class Printer {

    public void showCommandsList() {
        System.out.println("""
                Possible commands:
                get <high/low/open/close> yyyy.mm.dd hh:mm 
                get <high/low/open/close> yyyy.mm.dd hh
                get <high/low/open/close> yyyy.mm.dd
                get yyyy.mm.dd hh:mm
                get yyyy.mm.dd hh
                get yyyy.mm.dd
                volatility yyyy.mm.dd-hh:mm
                volatility yyyy.mm.dd-hh
                volatility yyyy.mm.dd
                most_volatile_day
                most_volatile_hour
                average_minutely_volatility
                average_hourly_volatility
                average_daily_volatility
                 
                commands - shows list possible commands
                example  - shows example command
                exit     - quit program
                """);
    }

    public void showMainMenu() {
        System.out.println("""
                commands - shows list all possible commands
                example  - shows example command
                exit     - quit program
                """);
    }

    public void showExampleCommands() {
        System.out.println("""
                get low 2020.11.02 13:55 -> Low=1.162340
                get 2020.11.02 13:55     -> Open=1.162390, High=1.162400, Low=1.162340, Close=1.162400
                most_volatile_hour       -> 2020-11-03 21:00
                """);
    }

    public void showWarningWrongCommand() {
        System.out.println("Wrong command");
    }

    public void showWarningNoElements() {
        System.out.println("That element doesn't exist in provided file");
    }

    public void exitCommand() {
        System.out.println("All commands and results are saved in the file. Good bye! ");
    }
}
