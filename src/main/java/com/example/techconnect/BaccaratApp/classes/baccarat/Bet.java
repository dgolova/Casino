package com.example.techconnect.BaccaratApp.classes.baccarat;

import com.example.techconnect.BaccaratApp.classes.Card;

import java.util.ArrayList;

public abstract class Bet {

    private int points;
    private ArrayList<Card> hand;

    public Bet() {
        this.points = 0;
        hand = new ArrayList<Card>();
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(Card card) {
        this.points += card.getValueFromRank();
        this.points %= 10;
    }

    public void addToHand(Card card){
        this.hand.add(card);
        this.addPoints(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int cardValue(){
        Card lastCard = null;
        if(returnSize() > 0){
            lastCard = this.hand.get(returnSize() - 1);
            return lastCard.getValueFromRank();
        }
        return 0;
    }

    public int returnSize(){
        return this.hand.size();
    }

}
