package com.example.techconnect.BaccaratApp.controllers;

import com.example.techconnect.BaccaratApp.models.BetLog;
import com.example.techconnect.BaccaratApp.models.User;
import com.example.techconnect.BaccaratApp.repositories.BetLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BetLogController {

    @Autowired
    BetLogRepository betLogRepository;

    @GetMapping(value = "/betlogs")
    public ResponseEntity<List<BetLog>> getBetLogs(){
        return new ResponseEntity<>(betLogRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/bet_log")
    public ResponseEntity<List<BetLog>> getUserBetLog(@PathVariable Long id){
        return new ResponseEntity<>(betLogRepository.findByUserId(id), HttpStatus.OK);

    }
}
