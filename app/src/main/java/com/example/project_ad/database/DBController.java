package com.example.project_ad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;

public class DBController {
    public static final String DATABASE_NAME = "project.db";
    public static final int DATABASE_VERSION = 1;
    private final Context context;

    public SQLiteDatabase database;

    public DBController(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DBController.DATABASE_NAME, null, DBController.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + DBTaskTof.TABLE_NAME + " (" +
                    DBTaskTof.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DBTaskTof.COLUMN_RUWORD+ " TEXT, " +
                    DBTaskTof.COLUMN_ENWORD + " TEXT, " +
                    DBTaskTof.COLUMN_BOOL + " BOOLEAN);";
            db.execSQL(query);

            String query1 = "CREATE TABLE " + DBTaskLetter.TABLE_NAME + " (" +
                    DBTaskLetter.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DBTaskLetter.COLUMN_WORD + " TEXT, " +
                    DBTaskLetter.COLUMN_DISTRACTORS + " TEXT, " +
                    DBTaskLetter.COLUMN_MISSING_LETTER + " INT);";
            db.execSQL(query1);

            String query2 = "CREATE TABLE " + DBScores.TABLE_NAME + " (" +
                    DBScores.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DBScores.COLUMN_SCORES + " INT, " +
                    DBScores.COLUMN_DATE + " TEXT, " +
                    DBScores.COLUMN_TYPE + " TEXT);";
            db.execSQL(query2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DBTaskTof.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DBTaskLetter.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + DBScores.TABLE_NAME);
            onCreate(db);
        }
    }
}
