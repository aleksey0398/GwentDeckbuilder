package com.artyom_panfilenko.gwentdeckbuilder;


public class DeckCard extends Card {

    int num;

    public DeckCard(int id, String name, String tags, String image, String description, String summon, String type, String rarity, String faction, int num) {
        super(id, name, tags, image, description, type, rarity, faction);
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
