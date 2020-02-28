package com.example.techconnect.BaccaratApp.DTO.blackJack;

import com.example.techconnect.BaccaratApp.DTO.Card;
import com.example.techconnect.BaccaratApp.DTO.Deck;
import com.example.techconnect.BaccaratApp.models.User;
import org.springframework.stereotype.Component;

@Component
public class BlackJack {

    private Deck deck;
    Player[] players;
    Dealer[] dealers;


    public BlackJack(Deck deck) {
        this.deck = deck;
        this.players = new Player[1000];
        this.dealers = new Dealer[1000];

        for(int i=0; i<1000; i++) {
            Player player = new Player();
            players[i] = player;
            Dealer dealer = new Dealer();
            dealers[i] = dealer;

        }
    }



    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Dealer[] getDealers() {
        return dealers;
    }

    public void setDealers(Dealer[] dealers) {
        this.dealers = dealers;
    }

    public Player selectPlayer(long id){
        int id1 = (int) id;
        return this.players[id1];
    }
    public Dealer selectDealer(long id){
        int id1 = (int) id;
        return this.dealers[id1];
    }


    public void checkDeck(){
        if (this.deck.getStack().size() == 0){
            this.setDeck(new Deck());
        }
    }

    public void deal2Cards(Player player){
        this.checkDeck();
        for (int e=0; e <2; e++){
            Card card = this.getDeck().dealCard();
            player.addCard(card);
        }
    }

    public void dealForPlayer(Player player){
        this.checkDeck();
        if (player.calculatePoints()<21){
            Card card = this.getDeck().dealCard();
            player.addCard(card);
        }
    }

    public void dealForDealer(Dealer dealer){
        this.checkDeck();
        this.deal2Cards(dealer);
        while (dealer.calculatePoints()<18){
            Card card = this.getDeck().dealCard();
            dealer.addCard(card);
        }
    }

    public boolean defineWinners(Player player, Dealer dealer) {
        if (player.calculatePoints() > 21) {
            return false;
        } else if (dealer.calculatePoints() > 21) {
            return true;
        } else {
            if (dealer.calculatePoints() > player.calculatePoints()) {
                return false;
            }
            else if (player.calculatePoints() == 21){
                return true;
            }
            else {
                return true;
            }
        }
    }


    public int cashOut(User user){
        if (this.defineWinners(this.selectPlayer(user.getId()),this.selectDealer(user.getId())) == true){
            return user.getWallet() +  user.getBetAmount();
        }
        return user.getWallet() - user.getBetAmount();
    }

    public  String getWinner(User user){
        if (this.defineWinners(this.selectPlayer(user.getId()),this.selectDealer(user.getId())) == true){
            return "YOU";
        }
        else {
            return "DEALER";
        }
    }

    public void restart(User user){
        this.selectPlayer(user.getId()).removeCards();
        this.selectDealer(user.getId()).removeCards();
    }






}



