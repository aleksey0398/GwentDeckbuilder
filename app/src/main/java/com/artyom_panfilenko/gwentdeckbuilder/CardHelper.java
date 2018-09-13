package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class CardHelper {

    SQLiteDatabase db;

    public CardHelper(Context context){
        DBHelper dbHelper = new DBHelper(context,"cards");
        db = dbHelper.getWritableDatabase();
    }
    long insert(String name,String faction,String tags,String description,String image,String rarity){
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COLUMN_NAME,name);
        cv.put(DBHelper.COLUMN_FACTION,faction);
        cv.put(DBHelper.COLUMN_TAGS,tags);
        cv.put(DBHelper.COLUMN_DESCRIPTION,description);
        cv.put(DBHelper.COLUMN_IMAGE,image);
        cv.put(DBHelper.COLUMN_RARITY,rarity);

        return db.insert(DBHelper.TABLE_CARDS_NAME,null,cv);
    }

    ArrayList<Card> getAll(){
        Cursor cursor = db.query(DBHelper.TABLE_CARDS_NAME, null, null, null, null, null, null);
        ArrayList<Card> arrayList = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()) do {
            int id = cursor.getInt(DBHelper.NUM_COLUMN_ID);
            Log.d("ID", Integer.toString(id));
            String name = cursor.getString(DBHelper.NUM_COLUMN_NAME);
            Log.d("NAME",name);
            String faction = cursor.getString(DBHelper.NUM_COLUMN_FACTION);
            Log.d("FACTION",faction);
            String description = cursor.getString(DBHelper.NUM_COLUMN_DESCRIPTION);
            String tags = cursor.getString(DBHelper.NUM_COLUMN_TAGS);
            String image = cursor.getString(DBHelper.NUM_COLUMN_IMAGE);
            String rarity = cursor.getString(DBHelper.NUM_COLUMN_RARITY);

            String type = new String("");
            switch (rarity){
                case "common":type = "bronze";break;
                case "rare": type = "bronze";break;
                case "epic": type = "silver";break;
                case "legendary": type = "gold";break;
            }
            arrayList.add(new Card(id,name,tags,image,description,type,rarity,faction));

        } while (cursor.moveToNext());
        db.close();
        return arrayList;
    }

}

