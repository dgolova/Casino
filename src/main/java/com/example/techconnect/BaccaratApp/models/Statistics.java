package com.example.techconnect.BaccaratApp.models;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "initial_funds")
    private int initialFunds;
    @Column
    private int earnings;
    @Column(name = "remaining_funds")
    private int remainingFunds;
    @Column(name = "bet_amount")
    private int betAmount;
    @Column(name = "bet_option")
    private String betOption;
    @Column(name = "winning_bet")
    private String winningBet;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    public Statistics(String name, int initialFunds, int earnings, int remainingFunds, int betAmount, String betOption, String winningBet, User user) {
        this.name = name;
        this.initialFunds = initialFunds;
        this.earnings = earnings;
        this.remainingFunds = remainingFunds;
        this.betAmount = betAmount;
        this.betOption = betOption;
        this.winningBet = winningBet;
//        this.user = user;
    }

    public Statistics(){

    }

//    public User getUser() {
//        return user;
//    }

//    public void setUser(User user) {
//        this.user = user;
//    }

    public int getRemainingFunds() {
        return remainingFunds;
    }

    public void setRemainingFunds(int remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public String getBetOption() {
        return betOption;
    }

    public void setBetOption(String betOption) {
        this.betOption = betOption;
    }

    public String getWinningBet() {
        return winningBet;
    }

    public void setWinningBet(String winningBet) {
        this.winningBet = winningBet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitialFunds() {
        return initialFunds;
    }

    public void setInitialFunds(int initialFunds) {
        this.initialFunds = initialFunds;
    }

    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
}
