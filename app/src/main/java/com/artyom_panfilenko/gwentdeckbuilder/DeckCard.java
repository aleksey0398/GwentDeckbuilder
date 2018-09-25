package com.artyom_panfilenko.gwentdeckbuilder;


public class DeckCard extends Card {

    int num;

    public DeckCard(int id, String name, String tags, String description, String type, String rarity, String faction, int num) {
        super(id, name, tags, description, type, rarity, faction);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
