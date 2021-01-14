package com.forexApp.io;

public class Printer {
    public void showMenu() {
        System.out.println("Enter a command: ");
        System.out.println("1 - get high\n2 - get low\n3 - get open\n4 - get close\n" +
                "5 - get date\n6 - get volatility\n7- most_volatile_day\n" +
                "8 - most_volatile_hour\n9 - average_minutely_volatility\n10 - average_hourly_volatility\n" +
                "11 - average_daily_volatility");
    }
}
