package com.hitechno.sogoods.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hitechno.sogoods.model.LocationMin;

public class GoogleGeoLocation {
	public static class AddressComponent {
		public static final String TYPE_ROUTE		= "route";
		public static final String TYPE_SUBLOCALITY	= "sublocality";
		public static final String TYPE_LOCALITY	= "locality";
		public static final String TYPE_ADMIN_AREA	= "administrative_area_level_1";
		public static final String TYPE_COUNTRY		= "country";
		
		private static final String KEY_LONG_NAME = "long_name";
		private static final String KEY_SHORT_NAME = "short_name";
		private static final String KEY_TYPES = "types";
		private String m_LongName;
		private String m_ShortName;
		private String[] m_Types;
		
		public AddressComponent(String longName, String shortName, String[] types) {
			m_LongName = longName;
			m_ShortName = shortName;
			m_Types = types;
		}
		
		public AddressComponent(JSONObject json) throws JSONException {
			m_LongName = json.getString(KEY_LONG_NAME);
			m_ShortName = json.getString(KEY_SHORT_NAME);
			JSONArray types = json.getJSONArray(KEY_TYPES);
			m_Types = new String[types.length()];
			for (int i = 0; i < types.length(); i++) {
				m_Types[i] = types.getString(i);
			}
		}
		
		public String getLongName() {
			return m_LongName;
		}
		
		public String getShortName() {
			return m_ShortName;
		}
		
		public String[] getTypes() {
			return m_Types;
		}
		
		public boolean hasType(String type) {
			for (int i = 0; i < m_Types.length; i++) {
				if (type.equals(m_Types[i])) return true;
			}
			return false;
		}
	}
	
	public static class ReverseGeoLocationResult {
		private static final String KEY_COMPS = "address_components";
		private static final String KEY_TYPES = "types";
		private AddressComponent[] m_Components;
		private String[] m_Types;
		
		public ReverseGeoLocationResult(AddressComponent[] components, String[] types) {
			m_Components = components;
			m_Types = types;
		}
		
		public ReverseGeoLocationResult(JSONObject json) throws JSONException {
			JSONArray components = json.getJSONArray(KEY_COMPS);
			m_Components = new AddressComponent[components.length()];
			for (int i = 0; i < components.length(); i++) {
				m_Components[i] = new AddressComponent(components.getJSONObject(i));
			}
			JSONArray types = json.getJSONArray(KEY_TYPES);
			m_Types = new String[types.length()];
			for (int i = 0; i < types.length(); i++) {
				m_Types[i] = types.getString(i);
			}
		}
		
		public AddressComponent[] getComponents() {
			return m_Components;
		}
		
		public String[] getTypes() {
			return m_Types;
		}
		
		public boolean hasType(String type) {
			for (int i = 0; i < m_Types.length; i++) {
				if (type.equals(m_Types[i])) return true;
			}
			return false;
		}
		
		public AddressComponent getComponentForType(String type) {
			for (int i = 0; i < m_Components.length; i++) {
				if (m_Components[i].hasType(type)) return m_Components[i];
			}
			return null;
		}
	}
	
	public static ReverseGeoLocationResult[] reverseLookup(double latitude, double longitude) {
		ReverseGeoLocationResult[] result = null;
		HashMap<String, String> params = new HashMap<String, String>();
		Formatter formatter = new Formatter(Locale.US);
		formatter.format("%f,%f", latitude, longitude);
		params.put("latlng", formatter.toString());
		InputStream resultIS = CommTools.callWebService("http://maps.googleapis.com/maps/api/geocode/json", params);
		formatter.close();
		if (resultIS != null) {
			String strJSON = CommTools.stringuifyIS(resultIS);
			try {
				resultIS.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				JSONObject json = new JSONObject(strJSON);
				JSONArray addresses = json.getJSONArray("results");
				result = new ReverseGeoLocationResult[addresses.length()];
				for (int i = 0; i < addresses.length(); i++) {
					result[i] = new ReverseGeoLocationResult(addresses.getJSONObject(i));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static LocationMin lookup(String address) {
		LocationMin result = null;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("address", address);
		InputStream resultIS = CommTools.callWebService("http://maps.googleapis.com/maps/api/geocode/json", params);
		if (resultIS != null) {
			String strJSON = CommTools.stringuifyIS(resultIS);
			try {
				resultIS.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				JSONObject json = new JSONObject(strJSON);
				String status = json.getString("status");
				if (status.equals("OK")) {
					JSONArray addresses = json.getJSONArray("results");
					JSONObject firstAddress = addresses.getJSONObject(0);
					JSONObject geometry = firstAddress.getJSONObject("geometry");
					JSONObject location = geometry.getJSONObject("location");
					double latitude = location.getDouble("lat");
					double longitude = location.getDouble("lng");
					result = new LocationMin(latitude, longitude, address);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
