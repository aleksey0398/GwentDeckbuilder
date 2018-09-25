package com.artyom_panfilenko.gwentdeckbuilder.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.artyom_panfilenko.gwentdeckbuilder.Card;

import java.util.ArrayList;

public class CardHelper {

    SQLiteDatabase db;

    public CardHelper(Context context) {
        CardDBHelper cardDbHelper = new CardDBHelper(context);
        db = cardDbHelper.getWritableDatabase();
    }

    public long insert(String name, String faction, String tags, String description,String rarity) {
        ContentValues cv = new ContentValues();
        cv.put(CardDBHelper.COLUMN_NAME, name);
        cv.put(CardDBHelper.COLUMN_FACTION, faction);
        cv.put(CardDBHelper.COLUMN_TAGS, tags);
        cv.put(CardDBHelper.COLUMN_DESCRIPTION, description);
        cv.put(CardDBHelper.COLUMN_RARITY, rarity);

        return db.insert(CardDBHelper.TABLE_CARDS_NAME, null, cv);
    }

    public ArrayList<Card> getAll() {
        Cursor cursor = db.query(CardDBHelper.TABLE_CARDS_NAME, null, null, null, null, null, null);
        ArrayList<Card> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) do {
            int id = cursor.getInt(CardDBHelper.NUM_COLUMN_ID);
            String name = cursor.getString(CardDBHelper.NUM_COLUMN_NAME);
            String faction = cursor.getString(CardDBHelper.NUM_COLUMN_FACTION);
            String description = cursor.getString(CardDBHelper.NUM_COLUMN_DESCRIPTION);
            String tags = cursor.getString(CardDBHelper.NUM_COLUMN_TAGS);
            String rarity = cursor.getString(CardDBHelper.NUM_COLUMN_RARITY);

            String type = new String("");
            switch (rarity) {
                case "common":
                    type = "bronze";
                    break;
                case "rare":
                    type = "bronze";
                    break;
                case "epic":
                    type = "silver";
                    break;
                case "legendary":
                    type = "gold";
                    break;
            }
            arrayList.add(new Card(id, name, tags,description, type, rarity, faction));

        } while (cursor.moveToNext());
        db.close();
        return arrayList;
    }
    public Card getCard(int id) {
        Cursor cursor = db.query(CardDBHelper.TABLE_CARDS_NAME, null, null, null, null, null, null);
        cursor.moveToPosition(id);
        String name = cursor.getString(CardDBHelper.NUM_COLUMN_NAME);
        String description = cursor.getString(CardDBHelper.NUM_COLUMN_DESCRIPTION);
        String tags = cursor.getString(CardDBHelper.NUM_COLUMN_TAGS);
        String rarity = cursor.getString(CardDBHelper.NUM_COLUMN_RARITY);
        String faction = cursor.getString(CardDBHelper.NUM_COLUMN_FACTION);
        String type = new String("");
        switch (rarity) {
            case "common":
                type = "bronze";
                break;
            case "rare":
                type = "bronze";
                break;
            case "epic":
                type = "silver";
                break;
            case "legendary":
                type = "gold";
                break;
        }
        return new Card(id, name, tags, description, type, rarity, faction);

    }

}

