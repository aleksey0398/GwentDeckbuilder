package com.artyom_panfilenko.gwentdeckbuilder;


import java.io.Serializable;

public class Deck implements Serializable {
    private int id;
    private String name;
    private String faction;
    private String leader;
    private String cards;

    public Deck(int id, String name, String faction, String leader, String cards) {
        this.id = id;
        this.name = name;
        this.faction = faction;
        this.leader = leader;
        this.cards = cards;
    }

    public Deck(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }
}
