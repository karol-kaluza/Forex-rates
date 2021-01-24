package com.forexApp.repository;

import java.util.ArrayList;
import java.util.List;

public class CommandsRepository {

    private static List<String>commandsList = new ArrayList<>();



    public List<String> getCommandsList() {
        return commandsList;
    }

    public void setCommandsList(List<String> commandsList) {
        this.commandsList = commandsList;
    }
}
