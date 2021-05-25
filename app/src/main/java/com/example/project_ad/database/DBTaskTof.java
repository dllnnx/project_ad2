package com.example.project_ad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project_ad.models.TaskLetter;
import com.example.project_ad.models.TaskTof;

import java.util.ArrayList;

public class DBTaskTof {

	public static final String TABLE_NAME = "tof";

	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_RUWORD = "ru_word";
	public static final String COLUMN_ENWORD = "en_word";
	public static final String COLUMN_BOOL = "bool";

	private final SQLiteDatabase dataBase;

	public DBTaskTof(Context context){
		dataBase = (new DBController(context)).database;
	}

	public ArrayList<TaskTof> getDefaultData(){
		ArrayList<TaskTof> tasks= new ArrayList<>();
		tasks.add(new TaskTof("кролик", "rabbit", 1));
		tasks.add(new TaskTof("собака", "dog", 1));
		tasks.add(new TaskTof("кошка", "animal", 0));
		tasks.add(new TaskTof("корова", "cow", 1));
		tasks.add(new TaskTof("стол", "bed", 0));
		tasks.add(new TaskTof("дружба", "friendship", 1));
		return tasks;
	}

	public long insert(String ruword, String enword, int bool){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_RUWORD, ruword);
		cv.put(COLUMN_ENWORD, enword);
		cv.put(COLUMN_BOOL, bool);
		return dataBase.insert(TABLE_NAME, null, cv);
	}

	private int update(TaskTof md){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_RUWORD, md.getRuWord());
		cv.put(COLUMN_ENWORD, md.getEnWord());
		cv.put(COLUMN_BOOL, md.getBool());
		return dataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[] { String.valueOf(md.getId())});
	}

	public void deleteAll() {
		dataBase.delete(TABLE_NAME, null, null);
	}

	public void delete(long id) {
		dataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
	}

	public TaskTof select(long id) {
		Cursor cursor = dataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

		cursor.moveToFirst();
		if (cursor.isAfterLast()){
			return null;
		}
		String RuWord = cursor.getString(cursor.getColumnIndex(COLUMN_RUWORD));
		String EnWord = cursor.getString(cursor.getColumnIndex(COLUMN_ENWORD));
		int Bool = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOL));
		return new TaskTof(id, RuWord, EnWord, Bool);
	}

	public ArrayList<TaskTof> selectAll(){
		Cursor cursor = dataBase.query(TABLE_NAME, null, null, null, null, null, null);

		ArrayList<TaskTof> arr = new ArrayList<>();
		cursor.moveToFirst();
		if(!cursor.isAfterLast()){
			do{
				long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
				String RuWord = cursor.getString(cursor.getColumnIndex(COLUMN_RUWORD));
				String EnWord = cursor.getString(cursor.getColumnIndex(COLUMN_ENWORD));
				int bool = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOL));
				arr.add(new TaskTof(id, RuWord, EnWord, bool));
			} while (cursor.moveToNext());
		}

		if(arr.size() == 0) return getDefaultData();
		return arr;
	}



}
