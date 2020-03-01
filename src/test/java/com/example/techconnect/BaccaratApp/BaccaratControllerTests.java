package com.example.techconnect.BaccaratApp;

import com.example.techconnect.BaccaratApp.DTO.Amount;
import com.example.techconnect.BaccaratApp.DTO.Response;
import com.example.techconnect.BaccaratApp.DTO.baccarat.Choice;
import com.example.techconnect.BaccaratApp.DTO.baccarat.Result;
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

public class BaccaratControllerTests {

    @Autowired
    TestRestTemplate restTemplate;


    @Test
    public void getPlay() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/user/1/baccarat/play", Result.class);
        Result result = response.getBody();
//        assertEquals(1300, result.getEarnings());

    }

    @Test
    public void postBetAmount() {
        Amount amount = new Amount(1);
        HttpEntity<Amount> request = new HttpEntity<>(amount);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/baccarat/bet/amount", request, Response.class);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void postBetAmountTooLarge() {
        Amount amount = new Amount(100000000);
        HttpEntity<Amount> request = new HttpEntity<>(amount);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/baccarat/bet/amount", request, Response.class);
        assertEquals(400, result.getStatusCodeValue());
    }
    @Test
    public void postBetChoice() {
        Choice choice = new Choice("PLAYER");
        HttpEntity<Choice> request = new HttpEntity<>(choice);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/baccarat/bet/choice", request, Response.class);
        assertEquals(200, result.getStatusCodeValue());
    }
    @Test
    public void postBetChoiceBad() {
        Choice choice = new Choice("something");
        HttpEntity<Choice> request = new HttpEntity<>(choice);
        ResponseEntity<Response> result = restTemplate.postForEntity("/user/1/baccarat/bet/choice", request, Response.class);
        assertEquals(400, result.getStatusCodeValue());
    }


}

