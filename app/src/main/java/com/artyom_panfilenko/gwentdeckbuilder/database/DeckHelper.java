package com.artyom_panfilenko.gwentdeckbuilder.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.artyom_panfilenko.gwentdeckbuilder.Deck;

import java.util.LinkedList;

public class DeckHelper

{
    SQLiteDatabase db;

    public DeckHelper(Context context) {
        DeckDBHelper cardDbHelper = new DeckDBHelper(context);
        db = cardDbHelper.getWritableDatabase();
    }

    public long insert(Deck deck) {
        ContentValues cv = new ContentValues();
        cv.put(DeckDBHelper.COLUMN_NAME, deck.getName());
        cv.put(DeckDBHelper.COLUMN_LEADER, deck.getLeader());
        cv.put(DeckDBHelper.COLUMN_FACTION, deck.getFaction());
        cv.put(DeckDBHelper.COLUMN_CARDS, deck.getCards());

        return db.insert(DeckDBHelper.TABLE_DECKS_NAME, null, cv);
    }


    public LinkedList<Deck> getAll() {
        Cursor cursor = db.query(DeckDBHelper.TABLE_DECKS_NAME, null, null, null, null, null, null);
        LinkedList<Deck> linkedList = new LinkedList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) do {
            int id = cursor.getInt(DeckDBHelper.NUM_COLUMN_ID);
            String name = cursor.getString(DeckDBHelper.NUM_COLUMN_NAME);
            String faction = cursor.getString(DeckDBHelper.NUM_COLUMN_FACTION);
            String leader = cursor.getString(DeckDBHelper.NUM_COLUMN_LEADER);
            String cards = cursor.getString(DeckDBHelper.NUM_COLUMN_CARDS);
            linkedList.add(new Deck(id, name, faction, leader, cards));

        } while (cursor.moveToNext());
        db.close();
        cursor.close();
        return linkedList;
    }



    public void update(Deck deck){
        ContentValues cv = new ContentValues();
        cv.put(DeckDBHelper.COLUMN_NAME, deck.getName());
        cv.put(DeckDBHelper.COLUMN_LEADER, deck.getLeader());
        cv.put(DeckDBHelper.COLUMN_FACTION, deck.getFaction());
        cv.put(DeckDBHelper.COLUMN_CARDS, deck.getCards());
        db.update(DeckDBHelper.TABLE_DECKS_NAME,cv,DeckDBHelper.COLUMN_ID + "=" + String.valueOf(deck.getId()),null);
    }

    public void delete(Deck deck){
        db.delete(DeckDBHelper.TABLE_DECKS_NAME,DeckDBHelper.COLUMN_ID + "=" + String.valueOf(deck.getId()),null);
    }

}
