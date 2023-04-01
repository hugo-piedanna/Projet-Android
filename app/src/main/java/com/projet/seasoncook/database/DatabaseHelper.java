package com.projet.seasoncook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.projet.seasoncook.models.Seasons;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SeasonCook.db";
    private static final int DATABASE_VERSION = 9;

    private String createTableSettings = "CREATE TABLE settings(id INTEGER PRIMARY KEY, season TEXT);";
    private String createTableAllergy = "CREATE TABLE allergy(name TEXT PRIMARY KEY);";
    private String insertInitParam = "INSERT INTO settings VALUES (1, '"+ Seasons.Spring.name() +"');";

    private String deleteTableSettings = "DROP TABLE IF EXISTS settings;";
    private String deleteTableAllergy = "DROP TABLE IF EXISTS allergy;";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.v("[Helper]", "Instance helper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("[Helper]", "Create db");
        db.execSQL(createTableSettings);
        db.execSQL(createTableAllergy);
        db.execSQL(insertInitParam);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("[Helper]", "Upgrade db");
        db.execSQL(deleteTableSettings);
        db.execSQL(deleteTableAllergy);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
