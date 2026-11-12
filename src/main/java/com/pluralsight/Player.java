package com.pluralsight;

public class Player {
    private String name;
    private Hand hand;

    public Player (String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void grabCard (Card card) {
        this.hand.deal(card);
    }

    public int getHandValue () {
        System.out.println("PLayer: " + this.name + " Hand: " + this.hand.getValue());
        return this.hand.getValue();
    }

}
