package com.example.project_ad.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_ad.models.MyScores;
import com.example.project_ad.models.TaskTof;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBScores {
    public static final String TABLE_NAME = "scores";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_SCORES = "scores";
    public static final String COLUMN_TYPE = "task_type";

    private final SQLiteDatabase dataBase;

    public DBScores(Context context){
        dataBase = (new DBController(context)).database;
    }

    public long insert(int scores, String taskType){
        ContentValues cv = new ContentValues();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());

        cv.put(COLUMN_DATE, simpleDateFormat.format(date));
        cv.put(COLUMN_SCORES, scores);
        cv.put(COLUMN_TYPE, taskType);
        return dataBase.insert(TABLE_NAME, null, cv);
    }

    public void deleteAll() {
        dataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        dataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public ArrayList<MyScores> selectAll(String taskType){
        Cursor cursor = dataBase.query(TABLE_NAME, null, COLUMN_TYPE + " = ?" ,
                new String[]{taskType}, null, null, null);

        ArrayList<MyScores> arr = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                int scores = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORES));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                arr.add(new MyScores(id, scores, date, type));
            } while (cursor.moveToNext());
        }
        return arr;
    }
}
