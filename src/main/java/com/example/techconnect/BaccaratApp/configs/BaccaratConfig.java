package com.example.techconnect.BaccaratApp.configs;

import com.example.techconnect.BaccaratApp.classes.Baccarat;
import com.example.techconnect.BaccaratApp.classes.BetChoice;
import com.example.techconnect.BaccaratApp.models.Statistics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class BaccaratConfig {

    @Bean
    public BetChoice getChoice(){
        return BetChoice.PLAYER;
    }

    @Bean
    public int getBetAmount(){
        return 0;
    }



}
