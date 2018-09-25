package com.artyom_panfilenko.gwentdeckbuilder.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DeckDBHelper extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "decks.db";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FACTION = "faction";
    public static final String COLUMN_LEADER = "leader";
    public static final String TABLE_DECKS_NAME = "decks";
    public static final String COLUMN_CARDS = "deck_cards";

    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_NAME = 1;
    public static final int NUM_COLUMN_FACTION = 2;
    public static final int NUM_COLUMN_LEADER = 3;
    public static final int NUM_COLUMN_CARDS = 4;

    public DeckDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DECKS_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_FACTION + " TEXT, " +
                COLUMN_LEADER + " TEXT, " +
                COLUMN_CARDS + " TEXT); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
