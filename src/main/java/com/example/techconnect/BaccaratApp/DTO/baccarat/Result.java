package com.example.techconnect.BaccaratApp.DTO.baccarat;

import com.example.techconnect.BaccaratApp.DTO.Card;

import java.util.List;

public class Result {
    private String winningBet;
    private List<Card> handPlayer;
    private int valuePlayerHand;
    private List<Card> handBanker;
    private int valueBankerHand;
    private int earnings;

    public Result(String winningBet, List<Card> handPlayer, int valuePlayerHand, List<Card> handBanker, int valueBankerHand, int ernings) {
        this.winningBet = winningBet;
        this.handPlayer = handPlayer;
        this.valuePlayerHand = valuePlayerHand;
        this.handBanker = handBanker;
        this.valueBankerHand = valueBankerHand;
        this.earnings = ernings;
    }

    public String getWinningBet() {
        return winningBet;
    }

    public void setWinningBet(String winningBet) {
        this.winningBet = winningBet;
    }

    public List<Card> getHandPlayer() {
        return handPlayer;
    }

    public void setHandPlayer(List<Card> handPlayer) {
        this.handPlayer = handPlayer;
    }

    public List<Card> getHandBanker() {
        return handBanker;
    }

    public void setHandBanker(List<Card> handBanker) {
        this.handBanker = handBanker;
    }

    public int getValuePlayerHand() {
        return valuePlayerHand;
    }

    public void setValuePlayerHand(int valuePlayerHand) {
        this.valuePlayerHand = valuePlayerHand;
    }

    public int getValueBankerHand() {
        return valueBankerHand;
    }

    public void setValueBankerHand(int valueBankerHand) {
        this.valueBankerHand = valueBankerHand;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }
}
