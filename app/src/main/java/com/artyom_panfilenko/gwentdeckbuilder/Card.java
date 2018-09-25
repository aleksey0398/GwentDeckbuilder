package com.artyom_panfilenko.gwentdeckbuilder;


public class Card {
    private int id;
    private String name;
    private String tags;
    private String description;
    private String type;
    private String rarity;
    private String faction;

    public Card(int id, String name, String tags, String description, String type, String rarity, String faction) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.description = description;

        this.type = type;
        this.rarity = rarity;
        this.faction = faction;
    }

    public Card(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
