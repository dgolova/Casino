package com.example.techconnect.BaccaratApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int wallet;
    @Column
    private String choice;
    @Column
    private int betAmount;


    @JsonBackReference
    @OneToMany (mappedBy = "user", fetch = FetchType.LAZY)
    private List<BetLog> betLog;


    public User(String name, int wallet) {
        this.name = name;
        this.wallet = wallet;
        this.choice = null;
        this.betAmount = 0;
    }

    public User(){

    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public List<BetLog> getBetLog() {
        return betLog;
    }

    public void setBetLog(List<BetLog> betLog) {
        this.betLog = betLog;
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
}
