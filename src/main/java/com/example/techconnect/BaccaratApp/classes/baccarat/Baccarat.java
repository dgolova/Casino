package com.example.techconnect.BaccaratApp.classes.baccarat;

import com.example.techconnect.BaccaratApp.classes.*;
import com.example.techconnect.BaccaratApp.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class Baccarat implements IShuffle {

    ArrayList<Deck> decks;
    ArrayList<Card> bigDeck;
    Banker banker;
    Player player;
    String handOfPlayer;
    String handOfBanker;
    private int totalMoneyEarned;
    private BetChoice winningBet;


    public String getHandOfPlayer() {
        return handOfPlayer;
    }

    public String getHandOfBanker() {
        return handOfBanker;
    }

    public Baccarat(BetChoice choice, int betAmount){
        this.winningBet = choice;
        decks = new ArrayList<Deck>();
        bigDeck = new ArrayList<Card>();
        banker = new Banker();
        player = new Player();
        handOfPlayer = "";
        handOfBanker = "";
        this.totalMoneyEarned = 0;

        Deck deck = new Deck();
        for(int i=0; i<6; i++){
            decks.add(deck);
        }
        for(Deck deck1: decks){
            for(Card card: deck1.getStack()){
                bigDeck.add(card);
            }
        }

    }

    public BetChoice getWinningBet() {
        return winningBet;
    }

    public void setWinningBet(BetChoice winningBet) {
        this.winningBet = winningBet;
    }

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public void setBigDeck(ArrayList<Card> bigDeck) {
        this.bigDeck = bigDeck;
    }

    public Banker getBanker() {
        return banker;
    }

    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setHandOfPlayer(String handOfPlayer) {
        this.handOfPlayer = handOfPlayer;
    }

    public void setHandOfBanker(String handOfBanker) {
        this.handOfBanker = handOfBanker;
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    public void setTotalMoneyEarned(int totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }


    public ArrayList<Card> getBigDeck() {

        return bigDeck;
    }

    public void createShuffledDeck() {
        Collections.shuffle(bigDeck);
    }

    public Card dealCard(){ //CHECKED
        Card card = null;
        if(bigDeck.size() > 0){
            card = bigDeck.get(0);
            bigDeck.remove(0);
        }
        return card;
    }

    public String cardsGiven(String toAddTo, Card card, Bet bet){
        toAddTo += card.cardName() + " with a value of " + bet.getPoints() + "\n";
        return toAddTo;
    }

    public BetChoice playGame() {

        Card card;

        createShuffledDeck();

        player.addToHand(this.dealCard());

        card = player.getHand().get(player.getHand().size() - 1);

        handOfPlayer += cardsGiven("Player has ", card, player);

        banker.addToHand(this.dealCard());

        card = banker.getHand().get(banker.getHand().size() - 1);

        handOfBanker += cardsGiven("Banker has ", card, banker);

        player.addToHand(dealCard());

        card = player.getHand().get(player.getHand().size() - 1);

        handOfPlayer += cardsGiven("Player has ", card, player);

        banker.addToHand(dealCard());

        card = banker.getHand().get(banker.getHand().size() - 1);

        handOfBanker += cardsGiven("Banker has ", card, banker);

        if (checkTie(player.getPoints(), banker.getPoints())) {
            return BetChoice.TIE;
        }
        BetChoice choice = compare(player.getPoints(), banker.getPoints());
        if (choice.equals(BetChoice.BANKER) && banker.getPoints() >= 8) {
            return BetChoice.BANKER;
        } else if (choice.equals(BetChoice.PLAYER) && player.getPoints() >= 8) {
            return BetChoice.PLAYER;
        }

        player.addToHand(dealCard());

        card = player.getHand().get(player.getHand().size() - 1);

        handOfPlayer += cardsGiven("Player has ", card, player);

        if (player.cardValue() == 9 || player.cardValue() == 10 || player.cardValue() == 1) {
            if (banker.getPoints() <= 3) {
                banker.addToHand(dealCard());
                card = banker.getHand().get(banker.getHand().size() - 1);
                handOfBanker += cardsGiven("Banker has ", card, banker);

            }
        } else if (player.cardValue() == 8) {
            if (banker.getPoints() <= 2) {
                banker.addToHand(dealCard());
                card = banker.getHand().get(banker.getHand().size() - 1);
                handOfBanker += cardsGiven("Banker has ", card, banker);

            }
        } else if (player.cardValue() == 6 || player.cardValue() == 7) {
            if (banker.getPoints() <= 6) {
                banker.addToHand(dealCard());
                card = banker.getHand().get(banker.getHand().size() - 1);
                handOfBanker += cardsGiven("Banker has ", card, banker);

            }
        } else if (player.cardValue() == 4 || player.cardValue() == 5) {
            if (banker.getPoints() <= 5) {
                banker.addToHand(dealCard());
                card = banker.getHand().get(banker.getHand().size() - 1);
                handOfBanker += cardsGiven("Banker has ", card, banker);

            }
        } else {
            if (banker.getPoints() <= 4) {
                banker.addToHand(dealCard());
                card = banker.getHand().get(banker.getHand().size() - 1);
                handOfBanker += cardsGiven("Banker has ", card, banker);

            }
        }

        if (checkTie(player.getPoints(), banker.getPoints())) {
            return BetChoice.TIE;
        }

        choice = compare(player.getPoints(), banker.getPoints());

        if (choice.equals(BetChoice.BANKER)) {
            return BetChoice.BANKER;
        } else if (choice.equals(BetChoice.PLAYER)) {
            return BetChoice.PLAYER;
        }

        return null;

    }

    public int cashOut(BetChoice winner, User user){
        int earnings = 0;
        if (user.getChoice().equalsIgnoreCase(winner.toString()) && winner.equals(BetChoice.PLAYER)) {
            user.setWallet(user.getBetAmount() * 3 + user.getWallet());
        }
        else if (user.getChoice().equalsIgnoreCase(winner.toString()) && winner.equals(BetChoice.BANKER)) {
            user.setWallet(user.getBetAmount() * 2 + user.getWallet());
        }
        else if (user.getChoice().equalsIgnoreCase(winner.toString()) && winner.equals(BetChoice.TIE)){
            user.setWallet(user.getBetAmount() * 6 + user.getWallet());
        }
        else {
            user.setWallet(user.getWallet() - user.getBetAmount());
        }
        earnings = user.getWallet();
        return earnings;

    }

    public boolean checkTie(int playerPoints, int bankerPoints){
        if(playerPoints == bankerPoints){
            return true;
        }
        return false;
    }

    public BetChoice compare(int playerPoints, int bankerPoints){

        if(playerPoints > bankerPoints){
            return BetChoice.PLAYER;
        }
        else if(bankerPoints > playerPoints){
            return BetChoice.BANKER;
        }
        return BetChoice.TIE;

    }

//    public int cashOutMoney(User user){
//        totalMoneyEarned = 10*user.getNoOfCoins().get(BetType.TEN) + 100*user.getNoOfCoins().get(BetType.HUNDRED) +
//                1000*user.getNoOfCoins().get(BetType.THOUSAND);
//
//        return totalMoneyEarned;
//    }

    public void restart(){
        banker = new Banker();
        player = new Player();
        handOfPlayer = "";
        handOfBanker = "";
    }


}
