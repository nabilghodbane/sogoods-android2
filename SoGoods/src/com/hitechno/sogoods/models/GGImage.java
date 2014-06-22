package com.hitechno.sogoods.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GGImage {

	public String link;
	public String thumbnailLink;

	public static ArrayList<GGImage> initArrayFromJson(JSONArray jArray) {
		ArrayList<GGImage> items = new ArrayList<GGImage>();
		try {
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject o = jArray.getJSONObject(i);
				GGImage item = new GGImage();
				item.link = o.getString("link");
				item.thumbnailLink = o.getJSONObject("image").getString(
						"thumbnailLink");
				if (item.link.substring(item.link.length() - 4,
						item.link.length()).contains("svg"))
					items.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return items;
	}

}
