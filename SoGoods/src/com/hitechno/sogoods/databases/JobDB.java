package com.hitechno.sogoods.databases;

import java.util.Formatter;

import com.hitechno.sogoods.model.Job;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class JobDB {
	private static JobDB m_Instance = null;
	
	public static JobDB getInstance() {
		if (m_Instance == null) {
			m_Instance = new JobDB();
		}
		return m_Instance;
	}
	
	private static final String CREATE_TABLE = "CREATE TABLE %s (id INTEGER PRIMARY KEY, name TEXT)";
	private static final String DELETE_TABLE = "DROP TABLE IF EXISTS %s";
	private SQLiteDatabase m_DB;
	
	protected JobDB() {
		m_DB = SQLiteDatabase.create(null);
	}
	
	
	public void fillDB(Job[] jobs, String language) {
		Formatter formatter = new Formatter();
		formatter.format("jobs_%s", language);
		String tableName = formatter.toString();
		formatter.close();
		formatter = new Formatter();
		formatter.format(DELETE_TABLE, tableName);
		m_DB.execSQL(formatter.toString());
		formatter.close();
		formatter = new Formatter();
		formatter.format(CREATE_TABLE, tableName);
		m_DB.execSQL(formatter.toString());
		formatter.close();
		ContentValues content = new ContentValues();
		for (int i = 0; i < jobs.length; i++) {
			content.clear();
			content.put("id", jobs[i].getID());
			content.put("name", jobs[i].getName());
			m_DB.insert(tableName, null, content);
		}
	}
	
	public Job[] getAllJobs(String language) {
		Formatter formatter = new Formatter();
		formatter.format("jobs_%s", language);
		String tableName = formatter.toString();
		formatter.close();
		Cursor cursor = m_DB.query(tableName, null, null, null, null, null, null);
		Job[] result = null;
		if (cursor.getCount() > 0) {
			result = new Job[cursor.getCount()];
			int columnIndexID = cursor.getColumnIndex("id");
			int columnIndexName = cursor.getColumnIndex("name");
			int index = 0;
			while (cursor.moveToNext()) {
				result[index++] = new Job(cursor.getInt(columnIndexID), cursor.getString(columnIndexName));
			}
		}
		cursor.close();
		return result;
	}
	
	public Job getJobForName(String language, String name) {
		Job result = null;
		Formatter formatter = new Formatter();
		formatter.format("jobs_%s", language);
		String tableName = formatter.toString();
		formatter.close();
		Cursor cursor = m_DB.query(tableName, null, "name = '" + name + "'", null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			int columnIndexID = cursor.getColumnIndex("id");
			int columnIndexName = cursor.getColumnIndex("name");
			result = new Job(cursor.getInt(columnIndexID), cursor.getString(columnIndexName));
		}
		cursor.close();
		return result;
	}
	
	public Job getJobForID(String language, int id) {
		Job result = null;
		Formatter formatter = new Formatter();
		formatter.format("jobs_%s", language);
		String tableName = formatter.toString();
		formatter.close();
		Cursor cursor = m_DB.query(tableName, null, "id = " + id, null, null, null, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			int columnIndexID = cursor.getColumnIndex("id");
			int columnIndexName = cursor.getColumnIndex("name");
			result = new Job(cursor.getInt(columnIndexID), cursor.getString(columnIndexName));
		}
		cursor.close();
		return result;
	}
}
