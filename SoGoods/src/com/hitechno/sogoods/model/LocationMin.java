package com.hitechno.sogoods.model;

public class LocationMin {
	private double m_Latitude;
	private double m_Longitude;
	private String m_Name;
	
	public LocationMin(double latitude, double longitude, String name) {
		m_Latitude = latitude;
		m_Longitude = longitude;
		m_Name = name;
	}
	
	public double getLatitude() {
		return m_Latitude;
	}
	
	public double getLongitude() {
		return m_Longitude;
	}
	
	public String getName() {
		return m_Name;
	}
}
