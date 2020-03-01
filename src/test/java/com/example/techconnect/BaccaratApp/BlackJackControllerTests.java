package com.example.techconnect.BaccaratApp;

import com.example.techconnect.BaccaratApp.DTO.Amount;
import com.example.techconnect.BaccaratApp.DTO.Response;
import com.example.techconnect.BaccaratApp.DTO.blackJack.Dealer;
import com.example.techconnect.BaccaratApp.DTO.blackJack.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BaccaratAppApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class BlackJackControllerTests {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    public void getCards() {
        ResponseEntity<Player> result1 = restTemplate.getForEntity("/user/1/blackjack/cards?player=player", Player.class);
        Player player = result1.getBody();
//        assertEquals(15, player.getValueOfCards());
        ResponseEntity<Dealer> result2 = restTemplate.getForEntity("/user/1/blackjack/cards?player=dealer", Dealer.class);
        Player dealer = result2.getBody();
//        assertEquals(15, dealer.getValueOfCards());
    }

    @Test
    public void postBetAmount() {
        Amount amount = new Amount(1);
        HttpEntity<Amount> request = new HttpEntity<>(amount);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/blackjack/bet_amount", request, Response.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void postBetAmountTooLarge() {
        Amount amount = new Amount(100000000);
        HttpEntity<Amount> request = new HttpEntity<>(amount);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/blackjack/bet_amount", request, Response.class);
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void postAdditionalCard() {
        Response agree = new Response();
        agree.setSuccess(true);
        HttpEntity<Response> request = new HttpEntity<>(agree);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/blackjack/additional_card", request, Response.class);
        assertEquals(201, result.getStatusCodeValue());
    }
}
