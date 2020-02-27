package com.example.techconnect.BaccaratApp.controllers;

import com.example.techconnect.BaccaratApp.classes.Amount;
import com.example.techconnect.BaccaratApp.classes.Card;
import com.example.techconnect.BaccaratApp.classes.Response;
import com.example.techconnect.BaccaratApp.classes.blackJack.BlackJack;
import com.example.techconnect.BaccaratApp.classes.blackJack.Player;
import com.example.techconnect.BaccaratApp.models.User;
import com.example.techconnect.BaccaratApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
public class BlackJackController {

    @Autowired
    BlackJack blackJack;

    Response response = new Response();

    @Autowired
    UserRepository userRepository;

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
        blackJack.deal2Cards(blackJack.getPlayer());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}//blackjack/cards") // get cards (as a string) fro player or dealer
    public ResponseEntity<Player> findCardsForPlayerOrDealer(
            @PathVariable long id,
            @RequestParam(name="player", required = false) String player){
        if(player.equalsIgnoreCase("player")){ //http://localhost:8080/blackjack/cards?player=player
            blackJack.getPlayer().setValueOfCards(blackJack.calculatePoints(blackJack.getPlayer()));
            return new ResponseEntity<>(blackJack.getPlayer(), HttpStatus.OK);
        }
        else if(player.equalsIgnoreCase("dealer")){ //http://localhost:8080/blackjack/cards?player=dealer
            blackJack.dealForDealer();
            blackJack.getDealer().setValueOfCards(blackJack.calculatePoints(blackJack.getDealer()));
            return new ResponseEntity<>(blackJack.getDealer(), HttpStatus.OK);
        }
        return new ResponseEntity<>(blackJack.getPlayer(), HttpStatus.OK);
    }

    @PostMapping(value = "/user/{id}/blackjack/additional_card")
    public ResponseEntity<Response> postAdditionalCard(@PathVariable long id, @RequestBody Response agree){
        if (agree.isSuccess() == true){
            blackJack.dealForPlayer(blackJack.getPlayer());
            agree.setResponse("Card was dealt, check your cards");
        }
        else{
            Optional<User> foundUser = userRepository.findById(id);
            User user = foundUser.get();
            user.setWallet(blackJack.cashOut(user));
            userRepository.save(user);
            agree.setResponse("Game is done");
            blackJack.getPlayer().removeCards();
            blackJack.getDealer().removeCards();
        }
        return new ResponseEntity<>(agree, HttpStatus.CREATED);
    }

//    @GetMapping(value = "blackjack/done")
//    public ResponseEntity<String> restartTheGame(){
////        blackJack.getPlayer().setBet(0);
////        blackJack.getPlayer().setName("");
////        blackJack.getPlayer().setProfit(0);
//        blackJack.getPlayer().removeCards();
//        blackJack.getDealer().removeCards();
//        return new ResponseEntity<>("Restart the Game", HttpStatus.OK);
//    }




//    @GetMapping(value = "/raids")
//    public ResponseEntity<List<Raid>> getAllRaids() {
//        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
//    }

//    @GetMapping(value = "/raids/{id}")
//    public ResponseEntity<Raid> getRaidById(@PathVariable Long id){
//        return new ResponseEntity(raidRepository.findById(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/raids")
//    public ResponseEntity<List<Raid>> findRaidsFilterByLocation(
//            @RequestParam(name="location", required = false) String location){
//        if(location != null){ //http://localhost:8080/raids?location=Tortuga
//            return new ResponseEntity<>(raidRepository.findByLocation(location), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
//    }

//    @PostMapping(value = "/pirates")
//    public ResponseEntity<Pirate> postPirate(@RequestBody Pirate pirate){
//        pirateRepository.save(pirate);
//        return new ResponseEntity<>(pirate, HttpStatus.CREATED);
//    }
//
//    @PutMapping(value = "pirates/{id}")
//    public ResponseEntity<Pirate> putPirate(@RequestBody Pirate pirate){
//        pirateRepository.save(pirate);
//        return new ResponseEntity<>(pirate, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "pirates/{id}")
//    public ResponseEntity<Long> deleatePirate(@PathVariable Long id){
//        pirateRepository.deleteById(id);
//        return new ResponseEntity<>(id, HttpStatus.OK);
//    }


}
