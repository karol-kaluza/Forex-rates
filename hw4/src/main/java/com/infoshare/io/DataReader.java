package com.infoshare.io;

import com.infoshare.model.ForexRecord;
import com.infoshare.repository.ForexRepository;
import com.infoshare.service.ForexService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {

    public static final String FILE_NAME = "DAT_MT_EURUSD_M1_202011.csv";
    private final ForexRepository forexRepository = new ForexRepository();
    private final ForexService forexService = new ForexService();
    private BufferedReader br;

    public void readFile() throws IOException {
        br = new BufferedReader(new FileReader(FILE_NAME));
        br.readLine();
        readContentFormFile();
    }

    private void readContentFormFile() throws IOException {
        String singleLine;
        while ((singleLine = br.readLine()) != null) {
            String[] content = singleLine.split(",");
            ForexRecord forexRecord = forexService.createForexRecord(content);
            forexRepository.getForexRecordsList().add(forexRecord);
        }
    }
}
