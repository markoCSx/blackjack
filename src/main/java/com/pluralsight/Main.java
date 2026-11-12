package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        String userText = " ";

        do {
            System.out.println("Enter anything to continue or \"x\" to exit");
            userText = userInput.nextLine();
            if (!userText.equalsIgnoreCase("x")) {
                playBlackjack();
            }
        } while (!userText.equalsIgnoreCase("x"));

        userInput.close();
    }

    public static void playBlackjack () {
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
            if (!players.get(0).isBusted() && !userText.equalsIgnoreCase("fold") && !players.get(0).isSitting()) {
                Card card = deck.deal();
                players.get(0).grabCard(card);
                System.out.println("Card: " + card.showCard());
                if (players.get(0).isBusted()){
                    System.out.println("You lost!");
                    System.out.println("Continue? (Y/N)");
                    userText = userInput.nextLine();
                    if (userText.equalsIgnoreCase("n")) {
                        return;
                    }
                }
            }
            players.get(0).display();
            for (int i = 1; i < players.size(); i++) {
                if (!players.get(i).isBusted() && !players.get(i).isSitting()) {
                    Card card = deck.deal();
                    players.get(i).grabCard(card);
                }
                players.get(i).display();
                if (players.get(i).isSitting()) {
                    isWinner(players, i);
                }
            }
            if (!players.get(0).isBusted() && isWinner(players, 0)) {
                return;
            }
            if (!players.get(0).isBusted() && !userText.equalsIgnoreCase("fold") && !players.get(0).isSitting()) {
                System.out.println("Fold?");
                userText = userInput.nextLine();
            }
        } while (!gameOver(players));

        userInput.close();
    }

    public static boolean isWinner (ArrayList<Player> players, int index) {
        int counter = 0;
        for (int i = 1; i < players.size(); i++) {
            if (i != index) {
                if (players.get(i).isBusted()) {
                    counter++;
                }
            }
        }
        if (counter == 3) {
            System.out.println("Player: " + players.get(index).getName() + " Wins!");
            return true;
        }
        return false;
    }

    public static boolean gameOver (ArrayList<Player> players) {
        int counter = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isBusted()) {
                counter++;
            }
        }
        if (counter == 3) {
            return true;
        }
        return false;
    }

}
