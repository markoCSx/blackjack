package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String userText = " ";



        while (!userText.equalsIgnoreCase("x")) {
            ArrayList<Player> players = new ArrayList<>();
            System.out.println("Enter anything to continue or \"x\" to exit");
            userText = userInput.nextLine();
            if (!userText.equalsIgnoreCase("x")) {
                Player.setCurrentPlayers(4);
                playBlackjack(players, userInput);
            }
        }

        userInput.close();
    }

    public static void playBlackjack (ArrayList<Player> players, Scanner userInput) {
        String userText = " ";

        if (players.isEmpty()) {
            String[] playerNames = {"Martin", "Cody", "Nick", "Ryan"};
            System.out.println("Enter player name: ");

            playerNames[0] = userInput.nextLine();
            for (int i = 0; i < playerNames.length; i++) {
                players.add(new Player(playerNames[i]));
            }
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
                Card card = deck.deal();
                players.get(i).grabCard(card);
            }
        }
    }

    public static boolean winner (ArrayList<Player> players, Scanner userInput) {
        ArrayList<Player> newPlayers = new ArrayList<>();
        ArrayList<Integer> indexs = new ArrayList<>();
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
                        index = i;
                    }
                    if (players.get(i).getHandValue() == highestHolder) {
                        indexs.add(i);
                    }
                }
            }
        }
        if (Player.getCurrentPlayers() == Player.getcurrentHolders()) {
            if (indexs.isEmpty()) {
                System.out.println(players.get(index).getName() + " WON!");
                return true;
            } else {
                indexs.add(index);
                for (int i = 0; i < indexs.size(); i++) {
                    newPlayers.add(players.get(indexs.get(i)));
                }
                Player.setCurrentPlayers(indexs.size());
                playBlackjack(newPlayers, userInput);
            }
        }
        return false;
    }

}
