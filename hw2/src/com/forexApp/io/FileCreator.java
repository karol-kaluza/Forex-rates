package com.forexApp.io;

import com.forexApp.repository.CommandsRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class FileCreator {

    private final LogWriter logWriter = new LogWriter();
    private final CommandsRepository commandsRepository = new CommandsRepository();

    public void createLogDirectory() {
        String dirName = "Logs";
        File dirFile = new File(dirName);
        boolean isDirExists = dirFile.exists();
        if (!isDirExists) {
            isDirExists = dirFile.mkdir();
        }
    }

    public void createLogFile() throws IOException {
        byte counter = 1;
        String fileName = "";
        File logFile = new File(fileName);
        boolean isLogFileExists = true;
        while (isLogFileExists) {
            fileName = "Logs/log_" + counter + "_" + LocalDate.now() + ".txt";
            counter++;
            logFile = new File(fileName);
            isLogFileExists = logFile.exists();
        }
        logFile.createNewFile();
        logWriter.saveLog(logFile, commandsRepository.getCommandsList() );
    }
}

