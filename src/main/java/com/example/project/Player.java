package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private ArrayList<Card> hand; // The player's personal cards
    private ArrayList<Card> allCards; // Combined personal and community cards
    String[] suits = Utility.getSuits(); // Card suits (♠, ♥, ♣, ♦)
    String[] ranks = Utility.getRanks(); // Card ranks (2, 3, ..., A)

    // Constructor: Initializes the hand and allCards lists
    public Player() {
        hand = new ArrayList<>();
        allCards = new ArrayList<>();
    }

    // Getter for the player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Getter for allCards (personal and community cards combined)
    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    // Adds a card to the player's hand and allCards list
    public void addCard(Card c) {
        hand.add(c);
        allCards.add(c);
    }

    // Evaluates the player's hand and determines the best poker hand
    public String playHand(ArrayList<Card> communityCards) {
        // Combine community cards with the player's hand into allCards
        allCards = new ArrayList<>(communityCards);
        allCards.addAll(hand);

        // Sort allCards by rank
        sortAllCards();

        // Calculate rank and suit frequencies
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        ArrayList<Integer> suitFrequency = findSuitFrequency();

        // Check for Royal Flush or Straight Flush
        if (isFlush(suitFrequency) && isStraight()) {
            if (allCards.get(allCards.size() - 1).getRank().equals("A")) {
                return "Royal Flush";
            }
            return "Straight Flush";
        }

        // Check for Four of a Kind
        if (hasFourOfAKind(rankFrequency)) {
            return "Four of a Kind";
        }

        // Check for Full House
        if (hasFullHouse(rankFrequency)) {
            return "Full House";
        }

        // Check for Flush
        if (isFlush(suitFrequency)) {
            return "Flush";
        }

        // Check for Straight
        if (isStraight()) {
            return "Straight";
        }

        // Check for Three of a Kind
        if (hasThreeOfAKind(rankFrequency)) {
            return "Three of a Kind";
        }

        // Check for Two Pair
        if (hasTwoPair(rankFrequency)) {
            return "Two Pair";
        }

        // Check for One Pair
        if (hasOnePair(rankFrequency)) {
            return "A Pair";
        }

        // Determine the highest card and its location
        Card highestCard = allCards.get(allCards.size() - 1); // The highest card in allCards
        boolean isHighestInHand = false;
        for (Card c : hand) {
            if (c.equals(highestCard)) {
                isHighestInHand = true;
                break;
            }
        }
        if (isHighestInHand) { // Check if the highest card is in the player's hand
            return "High Card";
        } else { // Otherwise, the highest card is in the community cards
            return "Nothing";
        }
    }

    // Uses selection sort to sort allCards by rank
    public void sortAllCards() {
        for (int i = 1; i < allCards.size(); i++) { // Loop through the cards
            int idx = i - 1; // Start comparing from the previous card
            while (idx >= 0 && Utility.getRankValue(allCards.get(idx + 1).getRank()) < Utility.getRankValue(allCards.get(idx).getRank())) {
                // Swap adjacent cards if out of order
                Card temp = allCards.get(idx + 1);
                allCards.set(idx + 1, allCards.get(idx));
                allCards.set(idx, temp);
                idx--; // Move backward
            }
        }
    }

    // Calculates the frequency of each rank in allCards
    public ArrayList<Integer> findRankingFrequency() {
        List<String> rankOrder = Arrays.asList(Utility.getRanks()); // Standard rank order
        ArrayList<Integer> frequency = new ArrayList<>();
        for (int i = 0; i < rankOrder.size(); i++) { // Initialize frequency counts
            frequency.add(0);
        }
        for (Card card : allCards) { // Count occurrences of each rank
            int idx = rankOrder.indexOf(card.getRank());
            frequency.set(idx, frequency.get(idx) + 1); // Increment count
        }
        return frequency;
    }

    // Calculates the frequency of each suit in allCards
    public ArrayList<Integer> findSuitFrequency() {
        List<String> suitOrder = Arrays.asList(Utility.getSuits()); // Standard suit order
        ArrayList<Integer> frequency = new ArrayList<>();
        for (int i = 0; i < suitOrder.size(); i++) { // Initialize frequency counts
            frequency.add(0);
        }
        for (Card card : allCards) { // Count occurrences of each suit
            int idx = suitOrder.indexOf(card.getSuit());
            frequency.set(idx, frequency.get(idx) + 1); // Increment count
        }
        return frequency;
    }

    // Helper method: Checks if the hand has a Flush (5 cards of the same suit)
    private boolean isFlush(ArrayList<Integer> suitFrequency) {
        for (int freq : suitFrequency) { // Check for any suit frequency >= 5
            if (freq >= 5) return true;
        }
        return false;
    }

    // Helper method: Checks if the hand has a Straight (5 consecutive ranks)
    private boolean isStraight() {
        ArrayList<Integer> rankValues = new ArrayList<>();
        for (Card card : allCards) { // Convert card ranks to numerical values
            rankValues.add(Utility.getRankValue(card.getRank()));
        }

        // Sort rank values in ascending order
        for (int i = 1; i < rankValues.size(); i++) {
            int idx = i - 1;
            while (idx >= 0 && rankValues.get(idx + 1) < rankValues.get(idx)) {
                int temp = rankValues.get(idx + 1);
                rankValues.set(idx + 1, rankValues.get(idx));
                rankValues.set(idx, temp);
                idx--; // Move backward
            }
        }

        // Check for 5 consecutive ranks
        int consecutiveCount = 1;
        for (int i = 1; i < rankValues.size(); i++) {
            if (rankValues.get(i) == rankValues.get(i - 1) + 1) {
                consecutiveCount++; // Increment for consecutive ranks
                if (consecutiveCount == 5) return true; // Found a straight
            } else if (rankValues.get(i) != rankValues.get(i - 1)) {
                consecutiveCount = 1; // Reset if not consecutive
            }
        }
        return false; // No straight found
    }

    // Check if the hand has Four of a Kind
    private boolean hasFourOfAKind(ArrayList<Integer> rankFrequency) {
        for (int freq : rankFrequency) {
            if (freq == 4) return true;
        }
        return false;
    }

    // Check if the hand has a Full House
    private boolean hasFullHouse(ArrayList<Integer> rankFrequency) {
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int freq : rankFrequency) {
            if (freq == 3) hasThree = true;
            if (freq == 2) hasTwo = true;
        }
        return hasThree && hasTwo;
    }

    // Check if the hand has Three of a Kind
    private boolean hasThreeOfAKind(ArrayList<Integer> rankFrequency) {
        for (int freq : rankFrequency) {
            if (freq == 3) return true;
        }
        return false;
    }

    // Check if the hand has Two Pair
    private boolean hasTwoPair(ArrayList<Integer> rankFrequency) {
        int pairCount = 0;
        for (int freq : rankFrequency) {
            if (freq == 2) pairCount++;
        }
        return pairCount == 2;
    }

    // Check if the hand has One Pair
    private boolean hasOnePair(ArrayList<Integer> rankFrequency) {
        int pairCount = 0;
        for (int freq : rankFrequency) {
            if (freq == 2) pairCount++;
        }
        return pairCount == 1;
    }

    // Converts the player's hand to a string representation
    @Override
    public String toString() {
        return hand.toString(); // Return hand as a string
    }
}
