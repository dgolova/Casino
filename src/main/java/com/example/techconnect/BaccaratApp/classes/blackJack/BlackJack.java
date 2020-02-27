package com.example.techconnect.BaccaratApp.classes.blackJack;

import com.example.techconnect.BaccaratApp.classes.Amount;
import com.example.techconnect.BaccaratApp.classes.Card;
import com.example.techconnect.BaccaratApp.classes.Deck;
import com.example.techconnect.BaccaratApp.classes.RankType;
import com.example.techconnect.BaccaratApp.models.User;
import org.springframework.stereotype.Component;

@Component
public class BlackJack {

    private Deck deck;
    private Player player;
    private Dealer dealer;


//    public BlackJack() {
//        this.deck = new Deck();
//        this.player = new Player();
//        this.dealer = new Dealer("Dealer");
//    }

    public BlackJack(Deck deck, Player player, Dealer dealer) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
    }



    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }




    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }






    public int findAce(Player player) {
        int i = 0;
        for (Card card : player.getCards()) {
            if (card.getRank().equals(RankType.ACE)) {
                i += 1;
            }
        }
        return i;
    }

    public int calculatePoints(Player player){
        int count = 0;
        for (int i=0; i<player.getCards().size(); i++){
            count += player.getCards().get(i).getValueFromRank();
        }
        for (int i=0; i<this.findAce(player); i++){
            if (count>21){
                count -=10;
            }
        }
        return count;
    }

//    public String showCards(Player player){
//        String show = "";
//        for (Card card: player.getCards()){
//            show += card.getRank().toString() +" of "+ card.getSuit().toString() +"  " ;
//            }
//        return show;
//    }

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
        if (this.calculatePoints(this.getPlayer())<21){
            Card card = this.getDeck().dealCard();
            this.getPlayer().addCard(card);
        }
    }

    public void dealForDealer(){
        this.checkDeck();
        this.deal2Cards(this.getDealer());
        while (this.calculatePoints(this.getDealer())<18){
            Card card = this.getDeck().dealCard();
            //System.out.printf("\n %s of %s    ", card.getRank(), card.getSuit() );
            this.getDealer().addCard(card);
        }
    }

    public boolean defineWinners() {
        if (this.calculatePoints(this.getPlayer()) > 21) {
            return false;
        } else if (this.calculatePoints(this.getDealer()) > 21) {
            return true;
        } else {
            if (this.calculatePoints(this.getDealer()) > this.calculatePoints(this.getPlayer())) {
                return false;
            }
            else if (this.calculatePoints(this.getPlayer()) == 21){
                return true;
            }
            else {
                return true;
            }
        }
    }

//    public String winLose(){
//        if (this.defineWinners() == true){
//            this.getPlayer().setProfit(+this.getPlayer().getBet());
//            return this.getPlayer().getName() + ", YOU WON!";
//        }
//        else {
//            this.getPlayer().setProfit(-this.getPlayer().getBet());
//            return "Sorry, "+ this.getPlayer().getName() +", you lost. \n";
//        }
//    }

    public int cashOut(User user){
        if (this.defineWinners()== true){
            return user.getWallet() +  user.getBetAmount();
        }
        return user.getWallet() - user.getBetAmount();
    }






}



