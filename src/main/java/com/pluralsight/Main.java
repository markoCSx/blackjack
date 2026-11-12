package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String userText = " ";
        ArrayList<Player> players = new ArrayList<>();
        String[] playerNames = {"Martin", "Cody", "Nick", "Ryan"};
        System.out.println("Enter player name: ");

        playerNames[0] = userInput.nextLine();
        for (int i = 0; i < playerNames.length; i++) {
            players.add(new Player(playerNames[i]));
        }
        Deck deck = new Deck();
        deck.shuffle();

        do {
            Card card = deck.deal();
            players.get(0).grabCard(card);
            System.out.println("Card: " + card.showCard());
            System.out.println("Hit or Fold");
            userText = userInput.nextLine();
        } while (players.get(0).getHandValue() <= 21 && !userText.equalsIgnoreCase("fold"));

        userInput.close();
    }
}
