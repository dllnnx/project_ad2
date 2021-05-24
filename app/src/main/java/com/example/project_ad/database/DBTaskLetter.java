package com.example.project_ad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project_ad.models.TaskLetter;

import java.util.ArrayList;

public class DBTaskLetter {
    public static final String TABLE_NAME = "letter";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DISTRACTORS = "distractors";
    public static final String COLUMN_MISSING_LETTER = "missing_letter";

    private SQLiteDatabase dataBase;

    public DBTaskLetter(Context context){
        dataBase = (new DBController(context)).database;
    }

    public ArrayList<TaskLetter> getDefaultData(){
        ArrayList<TaskLetter> taskLetters = new ArrayList<>();
        taskLetters.add(new TaskLetter("hello", 1, "qwer"));
		taskLetters.add(new TaskLetter("apple", 2, "qpjl"));
		taskLetters.add(new TaskLetter("boy", 1, "puop"));
		taskLetters.add(new TaskLetter("change", 2, "eyao"));
		taskLetters.add(new TaskLetter("multiplication", 4, "ioye"));
		taskLetters.add(new TaskLetter("addition", 3, "ioya"));
		return taskLetters;
    }

    public long insert(String word, String distractors, int missingLetter){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WORD, word);
        cv.put(COLUMN_DISTRACTORS, distractors);
        cv.put(COLUMN_MISSING_LETTER, missingLetter);
        return dataBase.insert(TABLE_NAME, null, cv);
    }

    private int update(TaskLetter taskLetter){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WORD, taskLetter.getWord());
        cv.put(COLUMN_DISTRACTORS, taskLetter.getDistractors());
        cv.put(COLUMN_MISSING_LETTER, taskLetter.getMissingLetter());
        return dataBase.update(
                TABLE_NAME, cv, COLUMN_ID + " = ?",
                new String[] { String.valueOf(taskLetter.getId())});
    }

    public void deleteAll() {
        dataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        dataBase.delete(TABLE_NAME,
                COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public TaskLetter select(long id) {
        Cursor cursor = dataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        if (cursor.isAfterLast()){
            return null;
        }
        String word = cursor.getString(cursor.getColumnIndex(COLUMN_WORD));
        String distractors = cursor.getString(cursor.getColumnIndex(COLUMN_DISTRACTORS));
        int missingLetter = cursor.getInt(cursor.getColumnIndex(COLUMN_MISSING_LETTER));
        return new TaskLetter(id, word, missingLetter, distractors);
    }

    public ArrayList<TaskLetter> selectAll(){
        Cursor cursor = dataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<TaskLetter> arr = new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()){
            do{
                long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                String word = cursor.getString(cursor.getColumnIndex(COLUMN_WORD));
                String distractors = cursor.getString(cursor.getColumnIndex(COLUMN_DISTRACTORS));
                int missingLetter = cursor.getInt(cursor.getColumnIndex(COLUMN_MISSING_LETTER));
                arr.add(new TaskLetter(id, word, missingLetter, distractors));
            } while (cursor.moveToNext());
        }
        if (arr.size() == 0) return getDefaultData();
        return arr;
    }

}
