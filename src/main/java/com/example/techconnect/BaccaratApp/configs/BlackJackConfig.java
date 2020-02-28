package com.example.techconnect.BaccaratApp.configs;

import com.example.techconnect.BaccaratApp.DTO.Deck;
import com.example.techconnect.BaccaratApp.DTO.blackJack.Dealer;
import com.example.techconnect.BaccaratApp.DTO.blackJack.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlackJackConfig {
    @Bean
    public Deck deck(){
        Deck deck = new Deck();
        deck.createShuffledDeck();
        return deck;
    }
    @Bean
    public Player player(){
        return new Player();
    }
    @Bean
    public Dealer dealer(){
        return new Dealer();
    }
}

