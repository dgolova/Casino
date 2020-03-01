package com.example.techconnect.BaccaratApp.DTO;

import java.util.*;

public class Deck {


    RankType rank;
    SuitType suit;
    ArrayList<Card> stack;


    public Deck() {
        this.stack = new ArrayList<Card>();
        for (RankType r : rank.values()) {
            for (SuitType s : suit.values()) {
                Card card = new Card(r, s);
                this.stack.add(card);
            }
        }
    }


    public ArrayList<Card> getDeck() {
        return stack;
    }

    public RankType getRank() {
        return rank;
    }

    public SuitType getSuit() {
        return suit;
    }

    public ArrayList<Card> getStack() {
        return stack;
    }

    public int getSize() {
        return stack.size();
    }



        public void createShuffledDeck() {

            Collections.shuffle(stack);
        }
        public Card dealCard() {
        Card cardRemoved = null;
        if(stack.size() > 0){
            cardRemoved = stack.remove(0);
        }
        return cardRemoved;
    }

}
