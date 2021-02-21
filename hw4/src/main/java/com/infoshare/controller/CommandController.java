package com.infoshare.controller;

import com.infoshare.model.ForexRecord;
import com.infoshare.service.CommandsGetService;
import com.infoshare.service.RecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/EURUSD")
public class CommandController {

    private final CommandsGetService commandsGetService;
    private final RecordService recordService;

    public CommandController(CommandsGetService commandsGetService, RecordService recordService) {
        this.commandsGetService = commandsGetService;
        this.recordService = recordService;
    }

    @GetMapping("/{yyyy}/{MM}/{dd}")
    public List<ForexRecord> geResultsByDay(@PathVariable("yyyy") int year,
                                            @PathVariable("MM") int month,
                                            @PathVariable("dd") int day) {
        List<ForexRecord> forexRecords = commandsGetService.getResultsByDay(year, month, day);
        recordService.saveForexRecord(forexRecords);
        return forexRecords;
    }

    @GetMapping("/{yyyy}/{MM}/{dd}/{hh}/{mm}")
    public List<ForexRecord> geResultsByTime(@PathVariable("yyyy") int year,
                                             @PathVariable("MM") int month,
                                             @PathVariable("dd") int day,
                                             @PathVariable("hh") int hour,
                                             @PathVariable("mm") int minute) {
        List<ForexRecord> forexRecords = commandsGetService.getResultsByTime(year, month, day, hour, minute);
        recordService.saveForexRecord(forexRecords);
        return forexRecords;
    }

}
