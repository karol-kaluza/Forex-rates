package com.infoshare.controller;

import com.infoshare.model.ForexRecord;
import com.infoshare.service.CommandsGetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/EURUSD")
public class CommandController {

    private final CommandsGetService commandsGetService;

    public CommandController(CommandsGetService commandsGetService) {
        this.commandsGetService = commandsGetService;
    }

    @GetMapping("/{yyyy}/{MM}/{dd}")
    public List<ForexRecord> geResultsByDay(@PathVariable("yyyy") int year,
                                            @PathVariable("MM") int month,
                                            @PathVariable("dd") int day) {
        return commandsGetService.getResultsByDay(year, month, day);
    }

    @GetMapping("/{yyyy}/{MM}/{dd}/{hh}/{mm}")
    public List<ForexRecord> geResultsByTime(@PathVariable("yyyy") int year,
                                             @PathVariable("MM") int month,
                                             @PathVariable("dd") int day,
                                             @PathVariable("hh") int hour,
                                             @PathVariable("mm") int minute) {
        return commandsGetService.getResultsByTime(year, month, day, hour, minute);
    }

}
