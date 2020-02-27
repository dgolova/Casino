package com.example.techconnect.BaccaratApp.classes.baccarat;

import com.example.techconnect.BaccaratApp.classes.baccarat.BetChoice;

public class Choice {

    private String betChoice;

    public Choice(String betChoice) {
        this.betChoice = betChoice;
    }

    public Choice(){}

    public BetChoice convertBetChoice(String betChoice){

        if(betChoice.equalsIgnoreCase("player")){
            return BetChoice.PLAYER;
        }
        else if (betChoice.equalsIgnoreCase("banker")) {
            return BetChoice.BANKER;
        }
        else if (betChoice .equalsIgnoreCase("tie")){
            return BetChoice.TIE;
        }
        return null;
    }

    public String convertBetChoice(BetChoice betChoice){

        if(betChoice == BetChoice.PLAYER){
            return "player";
        }
        else if (betChoice == BetChoice.BANKER) {
            return "banker";
        }
        else if (betChoice == BetChoice.TIE){
            return "tie";
        }
        return "error";
    }

    public String getBetChoice() {
        return betChoice;
    }

    public void setBetChoice(String betChoice) {
        this.betChoice = betChoice;
    }
}
