package com.example.techconnect.BaccaratApp.controllers;

import com.example.techconnect.BaccaratApp.DTO.Amount;
import com.example.techconnect.BaccaratApp.DTO.Response;
import com.example.techconnect.BaccaratApp.DTO.blackJack.BlackJack;
import com.example.techconnect.BaccaratApp.DTO.blackJack.Player;
import com.example.techconnect.BaccaratApp.models.BetLog;
import com.example.techconnect.BaccaratApp.models.User;
import com.example.techconnect.BaccaratApp.repositories.BetLogRepository;
import com.example.techconnect.BaccaratApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
public class BlackJackController {

    @Autowired
    BlackJack blackJack;

    Response response = new Response();

    @Autowired
    UserRepository userRepository;

    @Autowired
    BetLogRepository betLogRepository;

    @PostMapping(value = "/user/{id}/blackjack/bet_amount")
    public ResponseEntity<Response> putBetAmount (@PathVariable long id, @RequestBody Amount amount) {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.get();
        if(amount.getAmount() > user.getWallet()){
            response.setResponse("Bet amount exceeded, please try again");
            response.setSuccess(false);
            userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        user.setBetAmount(amount.getAmount());
        response.setResponse("Bet amount recorded");
        response.setSuccess(true);
        userRepository.save(user);
        blackJack.restart(user);
        blackJack.deal2Cards(blackJack.selectPlayer(user.getId()));
        blackJack.selectPlayer(user.getId()).recalculate();
        blackJack.dealForDealer(blackJack.selectDealer(user.getId()));
        blackJack.selectDealer(user.getId()).recalculate();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}/blackjack/cards")
    public ResponseEntity<Player> findCardsForPlayerOrDealer(@PathVariable long id, @RequestParam(name="player", required = false) String player){
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.get();
        if(player.equalsIgnoreCase("player")){
            return new ResponseEntity<>(blackJack.selectPlayer(user.getId()), HttpStatus.OK);
        }
        else if(player.equalsIgnoreCase("dealer")){
            user.setWallet(blackJack.cashOut(user));
            userRepository.save(user);
            BetLog betLog = new BetLog();
            betLog.setUser(user);
            betLog.setBetAmount(user.getBetAmount());
            betLog.setWinningBet(blackJack.getWinner(user));
            betLog.setGame("BlackJack");
            betLogRepository.save(betLog);
            return new ResponseEntity<>(blackJack.selectDealer(user.getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(blackJack.selectPlayer(user.getId()), HttpStatus.OK);
    }

    @PostMapping(value = "/user/{id}/blackjack/additional_card")
    public ResponseEntity<Response> postAdditionalCard(@PathVariable long id, @RequestBody Response agree){
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.get();
        if (agree.isSuccess() == true){
            blackJack.dealForPlayer(blackJack.selectPlayer(user.getId()));
            blackJack.selectPlayer(user.getId()).recalculate();
            agree.setResponse("Card was dealt, check your cards");
        }
        else{
            agree.setResponse("Game is done, check winners");
        }
        return new ResponseEntity<>(agree, HttpStatus.CREATED);
    }




}
