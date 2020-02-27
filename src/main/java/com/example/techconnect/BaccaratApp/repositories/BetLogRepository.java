package com.example.techconnect.BaccaratApp.repositories;

import com.example.techconnect.BaccaratApp.models.BetLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetLogRepository extends JpaRepository<BetLog, Long> {
}
