package com.forexApp.repository;

import com.forexApp.model.ForexRecord;
import java.util.ArrayList;
import java.util.List;

public class ForexRepository {

    private static final List<ForexRecord> forexRecordsList = new ArrayList<>();

    public List<ForexRecord> getForexRecordsList() {
        return forexRecordsList;
    }
}
