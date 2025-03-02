package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;






public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){  
            
        
        
        return "Nothing";
    }
    // uses selection sort
    public void sortAllCards() {
        ArrayList<Card> array = allCards;
        for (int i = 1; i < array.size(); i++) {
            int idx = i - 1;
            while (idx >= 0 && Utility.getRankValue(array.get(idx + 1).getRank()) < Utility.getRankValue(array.get(idx).getRank())) {
                Card temp = array.get(idx + 1);
                array.set(idx + 1, array.get(idx));
                array.set(idx, temp);
                idx--;
            }
        }
        allCards=array;
    }

   public ArrayList<Integer> findRankingFrequency(ArrayList<String> cards) {

    // Initialize an ArrayList to store the frequency of each rank

    ArrayList<Integer> frequency = new ArrayList<>(Collections.nCopies(13, 0));

    // Define rank order for reference
    List<String> ranks = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    // Iterate through the cards and calculate frequency
    for (String card : cards) {
        int index = ranks.indexOf(card);
        if (index != -1) {
            frequency.set(index, frequency.get(index) + 1);
        }
    }
    return frequency;
}


public ArrayList<Integer> findSuitFrequency(ArrayList<String> cards) {
    // Initialize an ArrayList to store the frequency of each suit
    ArrayList<Integer> frequency = new ArrayList<>(Collections.nCopies(4, 0));

    // Define suit order for reference
    List<String> suits = Arrays.asList("♠", "♥", "♣", "♦");

    // Iterate through the cards and calculate frequency
    for (String card : cards) {
        int index = suits.indexOf(card);
        if (index != -1) {
            frequency.set(index, frequency.get(index) + 1);
        }
    }

    return frequency;
}


   
    @Override
    public String toString(){
        return hand.toString();
    }




}
