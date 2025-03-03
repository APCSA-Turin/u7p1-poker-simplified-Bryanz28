package com.example.project;
import java.util.ArrayList;


public class Game{

    //This method is responsible for determining the winner between two players, p1 and p2.

    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        //variables for high cards
        int p1HighCard=0;

        int p2HighCard=0;

        if(Utility.getHandRanking(p1Hand)>Utility.getHandRanking(p2Hand)){ // player 1 wins if they have a better hand ranking

            return"Player 1 wins!";

        } else if(Utility.getHandRanking(p1Hand)<Utility.getHandRanking(p2Hand)){ // player 2 wins if they have a better hand ranking

            return"Player 2 wins!";

        } else if(Utility.getHandRanking(p1Hand)==Utility.getHandRanking(p2Hand)){ // if they have the same hand ranking we check high card

            if(Utility.getRankValue(p1.getHand().get(1).getRank())>=Utility.getRankValue(p1.getHand().get(0).getRank())){
                p1HighCard=Utility.getRankValue(p1.getHand().get(1).getRank());
            } else{
                p1HighCard=Utility.getRankValue(p1.getHand().get(0).getRank());
            }

            if(Utility.getRankValue(p2.getHand().get(1).getRank())>=Utility.getRankValue(p2.getHand().get(0).getRank())){
                p2HighCard=Utility.getRankValue(p2.getHand().get(1).getRank());
            } else{
                p2HighCard=Utility.getRankValue(p2.getHand().get(0).getRank());
            }

            if(p1HighCard>p2HighCard){
                return "Player 1 wins!";
            } else if (p1HighCard<p2HighCard){
                return "Player 2 wins!";
            }

        }
        return "Tie!";
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}