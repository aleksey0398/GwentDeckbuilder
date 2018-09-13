package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    String table;
    public static final String DATABASE_NAME = "decks.db";
    public static final int DATABASE_VERSION = 1;
    //общие колонки
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FACTION = "faction";
    //колонки таблицы карт
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TAGS = "tags";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_RARITY = "rarity";
    public static final String TABLE_CARDS_NAME = "cards";
    //колонки табилцы колод
    public static final String COLUMN_LEADER = "leader";
    public static final String TABLE_DECKS_NAME = "decks";
    public static final String COLUMN_CARDS = "deck_cards";

    //номера общих колонок
    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_NAME = 1;
    public static final int NUM_COLUMN_FACTION = 2;
    //номера колонок таблицы карт
    public static final int NUM_COLUMN_DESCRIPTION = 3;
    public static final int NUM_COLUMN_TAGS = 4;
    public static final int NUM_COLUMN_IMAGE = 5;
    public static final int NUM_COLUMN_RARITY = 6;
    //номера колонок таблицы колод
    public static final int NUM_COLUMN_LEADER = 3;
    public static final int NUM_COLUMN_CARDS = 4;

    public DBHelper(Context context,String table) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.table = table;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        if(table=="cards")
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CARDS_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_FACTION + " TEXT, " +
                COLUMN_TAGS + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_IMAGE + " TEXT, " +
                COLUMN_RARITY + "TEXT);" );
        else
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
