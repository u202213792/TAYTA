package com.tayta.Repositories;

import com.tayta.Entities.ClinicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {
}
