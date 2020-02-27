package com.example.techconnect.BaccaratApp.classes.blackJack;

import com.example.techconnect.BaccaratApp.classes.Card;
import java.util.ArrayList;


public class   Player {

    private ArrayList<Card> cards;
    private int valueOfCards;
//    private int profit;
//    private String name;
//    private int bet;



    public Player() {
        //this.name = name;
        this.cards = new ArrayList<Card>();
        this.valueOfCards = 0;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getValueOfCards() {
        return valueOfCards;
    }

    public void setValueOfCards(int valueOfCards) {
        this.valueOfCards = valueOfCards;
    }

    //    public int getProfit() {
//        return profit;
//    }
//
//    public void setProfit(int profit) {
//        this.profit = profit;
//    }
//
//    public int getBet() {
//        return bet;
//    }
//
//    public void setBet(int bet) {
//        this.bet = bet;
//    }
//
//    public String getName() {
//        return name;
//    }


    public ArrayList<Card> getCards() {
        return cards;
    }

//    public void setName(String name) {
//        this.name = name;
//    }


    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCards(){
        cards.clear();
    }




}
