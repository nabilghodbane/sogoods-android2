package com.hitechno.sogoods.model;

import org.json.JSONException;
import org.json.JSONObject;

public class City {
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private int m_ID;
	private String m_Name;
	
	public City(int id, String name) {
		m_ID = id;
		m_Name = name;
	}
	
	public City(JSONObject json) throws JSONException {
		m_ID = json.getInt(KEY_ID);
		m_Name = json.getString(KEY_NAME);
	}
	
	public int getID() {
		return m_ID;
	}
	
	public String getName() {
		return m_Name;
	}
}