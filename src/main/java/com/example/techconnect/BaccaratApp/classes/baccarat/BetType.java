package com.example.techconnect.BaccaratApp.classes.baccarat;

public enum BetType {

    TEN (10),
    HUNDRED (100),
    THOUSAND (1000);

    private int fund;

    BetType(int fund){
        this.fund = fund;
    }

    public int getFund(){
        return this.fund;
    }

}
