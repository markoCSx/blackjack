package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String ogPlayerName = "";

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String userText = " ";



        while (!userText.equalsIgnoreCase("x")) {
            ArrayList<Player> players = new ArrayList<>();
            System.out.println("Enter anything to continue or \"x\" to exit");
            userText = userInput.nextLine();
            if (!userText.equalsIgnoreCase("x")) {
                ogPlayerName = "";
                playBlackjack(players, userInput);
            }
        }

        userInput.close();
    }

    public static void playBlackjack (ArrayList<Player> players, Scanner userInput) {

        if (players.isEmpty()) {
            String[] playerNames = {"Martin", "Cody", "Nick", "Ryan", "Kheran", "Rick"};
            System.out.println("Enter player name: ");

            if (ogPlayerName.isEmpty()) {
                String playerName = userInput.nextLine();
                ogPlayerName = playerName;
                playerNames[0] = playerName;
            }

            for (int i = 0; i < playerNames.length; i++) {
                players.add(new Player(playerNames[i]));
            }
        }

        Player.setCurrentPlayers(players.size());

        for (int i = 0; i < players.size(); i++) {
            players.get(i).reset();
        }

        Deck deck = new Deck();
        deck.shuffle();

        do {

            dealPlayers(players, deck);
            if (players.get(0).isPlaying() && players.get(0).getName().equals(ogPlayerName)) {
                questionPlayer(userInput, players);
            } else {
                ogPlayerName = "";
            }

        } while (!winner(players, userInput));


    }

    public static void questionPlayer (Scanner userInput, ArrayList<Player> players) {
        System.out.println("Would you like to Hit or Hold");
        if (userInput.nextLine().equalsIgnoreCase("hold")) {
            players.get(0).setHold();
        }
    }

    public static void dealPlayers(ArrayList<Player> players, Deck deck) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isBusted() || players.get(i).isHolding()) {
                players.get(i).display();
            }
            if (players.get(i).isPlaying()) {
                if (Player.getCurrentPlayers() == 1) {
                    if (!players.get(i).isBusted()) {
                        System.out.println(players.get(i).getName() + " WON!");
                        return;
                    }
                }
                Card card = deck.deal();
                players.get(i).grabCard(card);
            }
        }
    }

    public static boolean winner (ArrayList<Player> players, Scanner userInput) {
        boolean tie = false;
        int index = 0;
        int highestHolder = 0;
        for (int i = 0; i < players.size(); i++) {
            if (Player.getCurrentPlayers() == 1) {
                if (!players.get(i).isBusted()) {
                    System.out.println(players.get(i).getName() + " WON!");
                    return true;
                }
            }
            if (Player.getCurrentPlayers() == Player.getcurrentHolders()) {
                if (!players.get(i).isBusted()) {
                    if (players.get(i).getHandValue() > highestHolder) {
                        highestHolder = players.get(i).getHandValue();
                        index = i;
                    } else if (players.get(i).getHandValue() == highestHolder) {
                        tie = true;
                    } else {
                        players.get(i).setBust();
                    }
                }
            }
        }
        if (Player.getCurrentPlayers() == Player.getcurrentHolders()) {
            if (!tie) {
                System.out.println(players.get(index).getName() + " WON!");
                return true;
            } else {
                playBlackjack(players, userInput);
                return true;
            }
        }
        return false;
    }

}
