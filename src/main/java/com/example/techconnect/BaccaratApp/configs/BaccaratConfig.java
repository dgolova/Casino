package com.example.techconnect.BaccaratApp.configs;

import com.example.techconnect.BaccaratApp.DTO.baccarat.BetChoice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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


    @Configuration
    public static class BlackJackConfig {

    }
}
