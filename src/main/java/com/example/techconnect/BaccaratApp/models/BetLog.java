package com.example.techconnect.BaccaratApp.models;

import com.example.techconnect.BaccaratApp.classes.BetChoice;
import com.sun.tools.javah.Gen;

import javax.persistence.*;

@Entity
@Table(name = "bet_logs")
public class BetLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bet_amount")
    private int betAmount;
    @Column(name = "bet_option")
    private String betOption;
    @Column(name = "winning_bet")
    private String winningBet;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public BetLog(int betAmount, String betOption, String winningBet, User user) {
        this.betAmount = betAmount;
        this.betOption = betOption;
        this.winningBet = winningBet;
        this.user = user;
    }

    public BetLog(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

