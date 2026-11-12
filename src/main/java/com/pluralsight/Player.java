package com.pluralsight;

public class Player {
    private String name;
    private Hand hand;
    private boolean busted;
    private boolean sit;

    public Player (String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void grabCard (Card card) {
        this.hand.deal(card);
    }

    public int getHandValue () {
        int handValue = this.hand.getValue();
        return handValue;
    }

    public void reset () {
        this.hand = new Hand();
        this.busted = false;
        this.sit = false;
    }

    public void display () {
        int handValue = this.hand.getValue();
        if (handValue <= 21) {
            System.out.println("PLayer: " + this.name + " Hand: " + handValue);
        }
        if (handValue > 21) {
            System.out.println("PLayer: " + this.name + " Hand: " + handValue + " BUSTED!");
            this.busted = true;
        }
        if (handValue == 21) {
            this.sit = true;
        }
    }

    public String getName () {
        return this.name;
    }

    public boolean isSitting () {
        return this.sit;
    }

    public void setSitting () {
        this.sit = true;
    }

    public boolean isBusted () {
        return this.busted;
    }
}
