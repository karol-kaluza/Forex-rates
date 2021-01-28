package com.forexApp.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogWriter {

    public void saveLog(File file, List<String> listLogs) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bfWriter = new BufferedWriter(fileWriter);

        for (String log : listLogs) {
            bfWriter.newLine();
            bfWriter.write(log);
        }
        bfWriter.flush();
        bfWriter.close();
    }
}
