package com.example.techconnect.BaccaratApp.controllers;

import com.example.techconnect.BaccaratApp.classes.*;
import com.example.techconnect.BaccaratApp.classes.baccarat.Baccarat;
import com.example.techconnect.BaccaratApp.classes.baccarat.BetChoice;
import com.example.techconnect.BaccaratApp.classes.baccarat.Choice;
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
public class BaccaratController {

    @Autowired
    Baccarat baccarat;

    @Autowired
    BetLogRepository betLogRepository;


    Response response = new Response();

    @Autowired
    UserRepository userRepository;



    @PostMapping(value = "/user/register")
    public ResponseEntity<User> userLogin (@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{id}/baccarat/bet/choice")
    public ResponseEntity<Response> putBetChoice (@PathVariable long id, @RequestBody Choice choice) {
        Optional<User> foundUserOptional = userRepository.findById(id);
        User user = foundUserOptional.get();
        BetChoice betChoice = choice.convertBetChoice(choice.getBetChoice());
        if(betChoice == BetChoice.PLAYER){
            user.setChoice(BetChoice.PLAYER.toString());
            response.setResponse("BetChoice recorded");
            response.setSuccess(true);
            userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if (betChoice == BetChoice.BANKER) {
            user.setChoice(BetChoice.BANKER.toString());
            response.setResponse("BetChoice recorded");
            response.setSuccess(true);
            //betLog.setBetOption(user.getChoice().toString());
            userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if (betChoice == BetChoice.TIE){
            user.setChoice(BetChoice.TIE.toString());
            response.setResponse("BetChoice recorded");
            response.setSuccess(true);
            //betLog.setBetOption(user.getChoice().toString());
            userRepository.save(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        userRepository.save(user);
        response.setResponse("Bad request");
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/user/{id}/baccarat/bet/amount")
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/user/{id}/baccarat/play")
    public ResponseEntity<Result> playGame (@PathVariable Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.get();
        BetChoice winningBet = baccarat.playGame();
        int earning = baccarat.cashOut(winningBet, user);
        BetLog betLog = new BetLog();
        betLog.setUser(user);
        betLog.setBetOption(user.getChoice().toString());
        betLog.setBetAmount(user.getBetAmount());
        betLog.setWinningBet(winningBet.toString());
        betLogRepository.save(betLog);
        Result result = new Result(winningBet.toString(), baccarat.getPlayer().getHand(),
                baccarat.getPlayer().getPoints(), baccarat.getBanker().getHand(),
                baccarat.getBanker().getPoints(), earning);
        baccarat.restart();
        userRepository.save(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

