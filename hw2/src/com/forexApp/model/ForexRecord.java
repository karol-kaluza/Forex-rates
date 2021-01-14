package com.forexApp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ForexRecord {

    private LocalDate date;
    private LocalTime time;
    private BigDecimal Open;
    private BigDecimal High;
    private BigDecimal Low;
    private BigDecimal Close;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getOpen() {
        return Open;
    }

    public void setOpen(BigDecimal open) {
        Open = open;
    }

    public BigDecimal getHigh() {
        return High;
    }

    public void setHigh(BigDecimal high) {
        High = high;
    }

    public BigDecimal getLow() {
        return Low;
    }

    public void setLow(BigDecimal low) {
        Low = low;
    }

    public BigDecimal getClose() {
        return Close;
    }

    public void setClose(BigDecimal close) {
        Close = close;
    }
}
