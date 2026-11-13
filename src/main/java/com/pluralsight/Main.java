package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String userText = " ";

        ArrayList<Player> players = new ArrayList<>();

        while (!userText.equalsIgnoreCase("x")) {
            players.clear();
            System.out.println("Enter anything to continue or \"x\" to exit");
            userText = userInput.nextLine();
            if (!userText.equalsIgnoreCase("x")) {
                playBlackjack(players, userInput);
            }
        }

        userInput.close();
    }

    public static void playBlackjack (ArrayList<Player> players, Scanner userInput) {

        if (players.isEmpty()) {
            String[] playerNames = {"Martin", "Cody", "Nick", "Ryan", "Kheran", "Rick"};
            System.out.println("Enter player name: ");


            String playerName = userInput.nextLine();
            playerNames[0] = playerName;

            for (int i = 0; i < playerNames.length; i++) {
                players.add(new Player(playerNames[i]));
            }

            Player.setCurrentPlayers(players.size());
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).reset();
        }

        Deck deck = new Deck();
        deck.shuffle();

        do {

            dealPlayers(players, deck);
            if (players.get(0).isPlaying()) {
                questionPlayer(userInput, players);
            }


        } while (!winner(players, userInput));


    }

    public static void questionPlayer (Scanner userInput, ArrayList<Player> players) {
        System.out.println("Would you like to Hit or Hold");
        String userText = "";
        userText = userInput.nextLine();
        if (userText.equalsIgnoreCase("hold")) {
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
            if (Player.getCurrentPlayers() == Player.getcurrentHolders()) {
                if (!players.get(i).isBusted()) {
                    if (players.get(i).getHandValue() > highestHolder) {
                        if (i != 0 && !players.get(0).isBusted()) {
                            players.get(index).setBust();
                        }
                        highestHolder = players.get(i).getHandValue();
                        index = i;
                    } else if (players.get(i).getHandValue() == highestHolder) {
                        tie = true;
                    } else {
                        players.get(i).setBust();
                    }
                }
            }
            if (Player.getCurrentPlayers() == 1) {
                if (!players.get(i).isBusted()) {
                    System.out.println(players.get(i).getName() + " WON!");
                    return true;
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
