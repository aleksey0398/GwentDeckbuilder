package com.artyom_panfilenko.gwentdeckbuilder.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CardDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cards.db";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FACTION = "faction";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_RARITY = "rarity";
    public static final String TABLE_CARDS_NAME = "cards";

    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_NAME = 1;
    public static final int NUM_COLUMN_FACTION = 2;
    public static final int NUM_COLUMN_DESCRIPTION = 3;
    public static final int NUM_COLUMN_TAGS = 4;
    public static final int NUM_COLUMN_RARITY = 5;

    public CardDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CARDS_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_FACTION + " TEXT, " +
                COLUMN_TAGS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_RARITY + " TEXT);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS_NAME);
                onCreate(sqLiteDatabase);
    }
}
