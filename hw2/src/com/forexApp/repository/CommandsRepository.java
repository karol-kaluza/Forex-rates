package com.forexApp.repository;

import java.util.ArrayList;
import java.util.List;

public class CommandsRepository {

    private static final List<String>commandsList = new ArrayList<>();

    public List<String> getCommandsList() {
        return commandsList;
    }
}
