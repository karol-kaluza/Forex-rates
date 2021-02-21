package com.infoshare.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ForexRecord {

    @Id
    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private LocalDate date;

    private LocalTime time;
    @Column (precision = 6, scale = 5)
    private BigDecimal Open;
    @Column (precision = 6, scale = 5)
    private BigDecimal High;
    @Column (precision = 6, scale = 5)
    private BigDecimal Low;
    @Column (precision = 6, scale = 5)
    private BigDecimal Close;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "ForexRecord: " +
                "date=" + date +
                ", time=" + time +
                ", Open=" + Open +
                ", High=" + High +
                ", Low=" + Low +
                ", Close=" + Close;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForexRecord that = (ForexRecord) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(Open, that.Open) &&
                Objects.equals(High, that.High) &&
                Objects.equals(Low, that.Low) &&
                Objects.equals(Close, that.Close);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, Open, High, Low, Close);
    }
}
