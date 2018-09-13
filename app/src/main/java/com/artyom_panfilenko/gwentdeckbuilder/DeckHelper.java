package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DeckHelper

{
    SQLiteDatabase db;

    public DeckHelper(Context context) {
        DBHelper dbHelper = new DBHelper(context, "decks");
        db = dbHelper.getWritableDatabase();
    }

    long insert(String name, String faction, String leader,String cards) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME, name);

        cv.put(DBHelper.COLUMN_LEADER, leader);
        cv.put(DBHelper.COLUMN_FACTION, faction);
        cv.put(DBHelper.COLUMN_CARDS, cards);

        return db.insert(DBHelper.TABLE_DECKS_NAME, null, cv);
    }

    ArrayList<Deck> getAll() {
        Cursor cursor = db.query(DBHelper.TABLE_DECKS_NAME, null, null, null, null, null, null);
        ArrayList<Deck> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()) do {
            int id = cursor.getInt(DBHelper.NUM_COLUMN_ID);
            Log.d("ID", Integer.toString(id));
            String name = cursor.getString(DBHelper.NUM_COLUMN_NAME);
            Log.d("NAME",name);
            String faction = cursor.getString(DBHelper.NUM_COLUMN_FACTION);
            Log.d("FACTION",faction);
            String leader = cursor.getString(DBHelper.NUM_COLUMN_LEADER);
            Log.d("LEADER",leader);
            String cards = cursor.getString(DBHelper.NUM_COLUMN_CARDS);
            Log.d("CARDS",cards);
            arrayList.add(new Deck(id, name, faction, leader, cards));

        } while (cursor.moveToNext());
        db.close();
        return arrayList;
    }
}
