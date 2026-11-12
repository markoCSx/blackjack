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

        int counter = 0;

        do {
            if (counter == players.size() && !gameOver(players)) {
                playBlackjack(newPlayers);
            }
            if (players.get(0).isBusted() || players.get(0).isSitting()) {
                players.get(0).display();
            }
            if (!players.get(0).isBusted() && !userText.equalsIgnoreCase("fold") && !players.get(0).isSitting()) {
                Card card = deck.deal();
                players.get(0).grabCard(card);
                card.showCard();
                players.get(0).display();
                if (players.get(0).isSitting()) {
                    counter++;
                }
                if (players.get(0).isBusted()){
                    newPlayers.remove(0);
                    counter++;
                    System.out.println("You lost!");
                    System.out.println("Continue? (Y/N)");
                    userText = userInput.nextLine();
                    if (userText.equalsIgnoreCase("n")) {
                        return;
                    }
                }
            }
            for (int i = 1; i < players.size(); i++) {
                if (players.get(i).isBusted()) {
                    players.get(i).display();
                }
                if (!players.get(i).isBusted() && !players.get(i).isSitting()) {
                    Card card = deck.deal();
                    card.showCard();
                    players.get(i).grabCard(card);
                    players.get(i).display();
                    if (players.get(i).isSitting()) {
                        newPlayers.add(players.get(i));
                        counter++;
                    }
                    if (players.get(i).isBusted()) {
                        counter++;
                    }
                }
                if (!players.get(i).isBusted() && isWinner(players, i)) {
                    return;
                }
            }
            if (!players.get(0).isBusted() && isWinner(players, 0)) {
                 return;
            }
            if (!players.get(0).isBusted() && !userText.equalsIgnoreCase("fold") && !players.get(0).isSitting()) {
                System.out.println("Fold?");
                userText = userInput.nextLine();
                if (userText.equalsIgnoreCase("fold")) {
                    players.get(0).setSitting();
                    counter++;
                }
            }
        } while (!gameOver(newPlayers));

        userInput.close();
    }

    public static boolean isWinner (ArrayList<Player> players, int index) {
        int counter = 0;
        for (int i = 0; i < players.size(); i++) {
            if (i != index) {
                if (players.get(i).isBusted()) {
                    counter++;
                }
            }
        }
        if (counter == players.size() - 1) {
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
        if (counter == players.size()) {
            return true;
        }
        return false;
    }

}
