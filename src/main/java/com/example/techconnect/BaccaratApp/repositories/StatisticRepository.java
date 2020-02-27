package com.example.techconnect.BaccaratApp.repositories;

import com.example.techconnect.BaccaratApp.models.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<Statistics, Long> {

}
