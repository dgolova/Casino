//import com.example.techconnect.BaccaratApp.classes.baccarat.Baccarat;
//import com.example.techconnect.BaccaratApp.classes.baccarat.BetChoice;
//import com.example.techconnect.BaccaratApp.classes.Participant;
//
//import java.util.*;
//
//import static java.lang.Thread.sleep;
//
//import static java.lang.Thread.sleep;
//
//public class Runner {
//    public static void main(String[] args) throws InterruptedException {
//
//        ArrayList<Participant> players = new ArrayList<Participant>();
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("How many players would you like to play with?");
//
//        int numOfPlayers = sc.nextInt();
//        Participant participant;
//        participant = null;
//
//        for(int i=0; i<numOfPlayers; i++){
//            String hand = "";
//            System.out.println("Who are you betting on (com.example.techconnect.BaccaratApp.classes.baccarat.Banker, com.example.techconnect.BaccaratApp.classes.baccarat.Player, Tie): com.example.techconnect.BaccaratApp.classes.baccarat.Player "+ (int)(i+1));
//
//            while(true){
//                hand = sc.next();
//
//                if(hand.equalsIgnoreCase("com.example.techconnect.BaccaratApp.classes.baccarat.Banker") || hand.equalsIgnoreCase("com.example.techconnect.BaccaratApp.classes.baccarat.Player")
//                        || hand.equalsIgnoreCase("Tie")){
//                    break;
//                }
//                else{
//                    System.out.println("Re-enter who you're betting on (com.example.techconnect.BaccaratApp.classes.baccarat.Banker, com.example.techconnect.BaccaratApp.classes.baccarat.Player, Tie)");
//                }
//            }
//
//
//
//            System.out.println("How much are you willing to bet: com.example.techconnect.BaccaratApp.classes.baccarat.Player "+ (int)(i+1));
//
//            while(true){
//                int amountOfBet = sc.nextInt();
//                if(amountOfBet <= 11100){
//                    if(hand.equalsIgnoreCase("com.example.techconnect.BaccaratApp.classes.baccarat.Banker")){
//                        participant = new Participant(BetChoice.BANKER, amountOfBet);
//                    }
//                    else if(hand.equalsIgnoreCase("com.example.techconnect.BaccaratApp.classes.baccarat.Player")){
//                        participant = new Participant(BetChoice.PLAYER, amountOfBet);
//                    }
//                    else if(hand.equalsIgnoreCase("Tie")){
//                        participant = new Participant(BetChoice.TIE, amountOfBet);
//                    }
//                    players.add(participant);
//                    break;
//                }
//                else{
//                    System.out.println("Do not bet what you cannot afford! com.example.techconnect.BaccaratApp.classes.baccarat.Bet again.");
//                }
//            }
//
//        Baccarat game = new Baccarat(players);
//
//        BetChoice winner = game.playGame();
//
//        System.out.println(game.getHandOfPlayer());
//        sleep(800);
//        System.out.println(game.getHandOfBanker());
//        sleep(800);
//        System.out.print("The winning bet is (drumrolls...): ");
//        sleep(1500);
//        System.out.println(winner.toString());
//
//
//        HashMap<Participant, Integer> earnings = game.cashOut(winner);
//
//        System.out.println();
//        sleep(1000);
//
//        int k = 0;
//        for(Participant player: players){
//            k++;
//            System.out.println("com.example.techconnect.BaccaratApp.classes.baccarat.Player "+ k+ " earned a total of "+ earnings.get(player));
//        }
//
//    }
//}
