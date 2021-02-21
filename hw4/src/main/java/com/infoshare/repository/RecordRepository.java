package com.infoshare.repository;

import com.infoshare.model.ForexRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository extends JpaRepository<ForexRecord, UUID> {
}
