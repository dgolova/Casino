package com.example.techconnect.BaccaratApp.DTO.blackJack;

import com.example.techconnect.BaccaratApp.DTO.Card;
import com.example.techconnect.BaccaratApp.DTO.RankType;

import java.util.ArrayList;


public class   Player {

    private ArrayList<Card> cards;
    private int valueOfCards;



    public Player() {
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

    public void recalculate() {

        this.valueOfCards = this.calculatePoints();
    }


    public ArrayList<Card> getCards() {
        return cards;
    }



    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCards(){
        cards.clear();
    }

    public int findAce() {
        int i = 0;
        for (Card card : this.getCards()) {
            if (card.getRank().equals(RankType.ACE)) {
                i += 1;
            }
        }
        return i;
    }

    public int calculatePoints(){
        int count = 0;
        for (int i=0; i<this.getCards().size(); i++){
            count += this.getCards().get(i).getValueFromRank();
        }
        for (int i=0; i<this.findAce(); i++){
            if (count>21){
                count -=10;
            }
        }
        return count;
    }





}
