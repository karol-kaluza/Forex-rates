package com.forexApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Factory {

    private final List<ForexRecord> forexRecords = new ArrayList<>();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public ForexRecord createForexRecord(String[] content) {
        ForexRecord forexRecord = new ForexRecord();
        forexRecord.setDate(LocalDate.parse(content[0], dateFormatter));
        forexRecord.setTime(LocalTime.parse(content[1], timeFormatter));
        forexRecord.setOpen(BigDecimal.valueOf(Double.parseDouble(content[2])));
        forexRecord.setHigh(BigDecimal.valueOf(Double.parseDouble(content[3])));
        forexRecord.setLow(BigDecimal.valueOf(Double.parseDouble(content[4])));
        forexRecord.setClose(BigDecimal.valueOf(Double.parseDouble(content[5])));
        return forexRecord;
    }
    public List<ForexRecord> getForexRecords() {
        return forexRecords;
    }
}
