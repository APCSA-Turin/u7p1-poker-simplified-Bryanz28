package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {

    private ArrayList<Card> hand;
    private ArrayList<Card> allCards;

    public Test(){
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void sortAllCards() {
        ArrayList<Card> array = allCards;
        for (int i = 1; i < array.size(); i++) {
            int idx = i - 1;
            System.out.println(idx);
            while (idx >= 0 && Utility.getRankValue(array.get(idx + 1).getRank()) < Utility.getRankValue(array.get(idx).getRank())) {
                Card temp = array.get(idx + 1);
                array.set(idx + 1, array.get(idx));
                array.set(idx, temp);
                idx--;
            }
        }
        allCards=array;
    }
        
        

   public static void main(String[] args) {
    
}


}
