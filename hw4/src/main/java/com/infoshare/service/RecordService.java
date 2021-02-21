package com.infoshare.service;

import com.infoshare.model.ForexRecord;
import com.infoshare.repository.RecordRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void saveForexRecord (List<ForexRecord> forexRecords){
        for (ForexRecord record:forexRecords){
            recordRepository.save(record);
        }
    }
}
