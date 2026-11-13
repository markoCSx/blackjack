package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String userText = " ";

        ArrayList<Player> players = new ArrayList<>();

        while (!userText.equalsIgnoreCase("x")) {
            System.out.println("Enter anything to continue or \"x\" to exit");
            userText = userInput.nextLine();
            if (!userText.equalsIgnoreCase("x")) {
                playBlackjack(players);
            }
        }

        userInput.close();
    }

    public static void playBlackjack (ArrayList<Player> players) {
        Scanner userInput = new Scanner(System.in);
        String userText = " ";

        for (int i = 0; i < players.size(); i++) {
            players.get(i).reset();
        }

        if (players.isEmpty()) {
            String[] playerNames = {"Martin", "Cody", "Nick", "Ryan"};
            System.out.println("Enter player name: ");

            playerNames[0] = userInput.nextLine();
            for (int i = 0; i < playerNames.length; i++) {
                players.add(new Player(playerNames[i]));
            }
        }

        ArrayList<Player> newPlayers = new ArrayList<>();
        newPlayers.add(players.get(0));

        Deck deck = new Deck();
        deck.shuffle();

        do {

            dealPlayers(players, deck);

        } while (!winner());


        userInput.close();
    }

    public static void dealPlayers(ArrayList<Player> players, Deck deck) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isPlaying()) {
                Card card = deck.deal();
                players.get(i).grabCard(card);
            }
        }
    }

    public static boolean winner () {
        return true;
    }

}
