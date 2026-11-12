package com.pluralsight;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        String[] playerNames = {"Martin", "Cody", "Nick", "Ryan"};
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i]));
        }
        Deck deck = new Deck();
        deck.shuffle();


        Hand hand1 = new Hand();


        Card card = deck.deal();
        hand1.deal(card);

        int handValue = hand1.getValue();
        System.out.println("This hand is worth " + handValue);
    }
}
