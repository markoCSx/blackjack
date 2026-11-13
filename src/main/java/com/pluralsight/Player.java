package com.pluralsight;

public class Player {
    private String name;
    private Hand hand;
    private boolean busted;
    private boolean holding;
    private static int currentPlayers = 0;
    private static int currentHolders = 0;

    public Player (String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public static int getCurrentPlayers () {
        return currentPlayers;
    }

    public static int getcurrentHolders () {
        return currentHolders;
    }

    public static void setcurrentHolders (int number) {
        currentHolders = number;
    }

    public static void setCurrentPlayers (int number) {
        currentPlayers = number;
    }

    public boolean isPlaying () {
        if (this.busted || this.holding) {
            return false;
        }
        return true;
    }

    public void grabCard (Card card) {
        this.hand.deal(card);
        int handValue = this.hand.getValue();
        if (handValue > 21) {
            setBust();
        }
        if (handValue == 21) {
            setHold();
        }
        display();
    }

    public int getHandValue () {
        int handValue = this.hand.getValue();
        return handValue;
    }

    public void reset () {
        this.hand = new Hand();
        this.holding = false;
        setcurrentHolders(0);
    }

    public void display () {
        int handValue = this.hand.getValue();
        String sDisplay = "PLayer: " + this.name + " Hand: " + handValue;
        if (handValue <= 21) {
            System.out.println(sDisplay);
        } else {
            System.out.println(sDisplay + " BUSTED!");
        }
    }

    public String getName () {
        return this.name;
    }

    public void setHold() {
        this.holding = true;
        currentHolders++;
    }

    public void setBust() {
        this.busted = true;
        if (isHolding()) {
            currentHolders--;
            this.holding = false;
        }
        currentPlayers--;
    }

    public boolean isHolding () {
        return this.holding;
    }

    public boolean isBusted () {
        return this.busted;
    }
}
