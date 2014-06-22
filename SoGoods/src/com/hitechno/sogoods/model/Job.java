package com.hitechno.sogoods.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.Html;

public class Job {
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "code";
	private int m_ID;
	private String m_Name;
	
	public Job(int id, String name) {
		m_ID = id;
		m_Name = name;
	}
	
	public Job(JSONObject json) throws JSONException {
		m_ID = json.getInt(KEY_ID);
		m_Name = Html.fromHtml(json.getString(KEY_NAME)).toString();
	}
	
	public int getID() {
		return m_ID;
	}
	
	public String getName() {
		return m_Name;
	}
}