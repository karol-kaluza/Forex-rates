package com.infoshare.service;

import com.infoshare.io.DataReader;
import com.infoshare.model.ForexRecord;
import com.infoshare.repository.ForexRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandsGetService {

    private final ForexRepository forexRepository = new ForexRepository();
    private final DataReader dataReader = new DataReader();

    public List<ForexRecord> getResultsByDay(int year, int month, int day) {
        return filterByDate(year, month, day);
    }

    public List<ForexRecord> getResultsByTime(int year, int month, int day, int hour, int minute) {
        LocalTime time = LocalTime.of(hour, minute);
        return filterByDate(year, month, day).stream()
                .filter(forexRecord -> forexRecord.getTime().equals(time))
                .collect(Collectors.toList());
    }

    private List<ForexRecord> filterByDate(int year, int month, int day) {
        try {
            dataReader.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDate date = LocalDate.of(year, month, day);
        return forexRepository.getForexRecordsList().stream()
                .filter(forexRecord -> forexRecord.getDate().equals(date))
                .collect(Collectors.toList());
    }
}


