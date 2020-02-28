package com.example.techconnect.BaccaratApp.DTO;

public class Card {

    private SuitType suit;
    private RankType rank;



    public Card(RankType rank, SuitType suit) {
        this.suit = suit;
        this.rank = rank;
    }

    public SuitType getSuit() {
        return suit;
    }

    public RankType getRank() {
        return rank;
    }

    public String cardName(){
        return String.format("%s of %s", this.rank, this.suit);
    }

    public int getValueFromRank(){
        return rank.getValue();
    }


}
