package com.hitechno.sogoods.models;

import java.util.List;

import org.json.JSONObject;

import android.content.ContentValues;

import com.hitechno.sogoods.constant.ConstantsHelper;

/**
 * Deserializes data that represents the <code>Store</code> model.
 * 
 */
public class Store {

	public int id;
	public int trueId;
	public String updatedAt;

	public String address;
	public String address2;
	public String addressUrl;
	public List<Integer> brandIds;
	public String brandIdsString;
	public String cityID;
	public String cityName;
	public String dateCreated;
	public double distance;
	public String fax_number;
	public String geocodingAccuracy;
	public double latitude;
	public double longitude;
	public String name;
	public String phone;
	public int storeID;
	public String type;
	public String zipcode;

	public static Store initUnitFromJson(JSONObject paramJSONObject) {
		Store localStore = new Store();
		try {
			localStore.storeID = paramJSONObject.getInt("idpos");
			localStore.name = paramJSONObject.getString("name");
			localStore.latitude = paramJSONObject.getDouble("latitude");
			localStore.longitude = paramJSONObject.getDouble("longitude");
			localStore.addressUrl = paramJSONObject.getString("website");
			localStore.type = paramJSONObject.getString("type");
			localStore.phone = paramJSONObject.getString("phone_number");
			localStore.address = paramJSONObject.getString("address_1");
			localStore.address2 = paramJSONObject.getString("address_2");
			localStore.cityID = paramJSONObject.getString("idcity");
			localStore.cityName = paramJSONObject.getString("city_name");
			localStore.fax_number = paramJSONObject.getString("fax_number");
			localStore.zipcode = paramJSONObject.getString("zipcode");
			localStore.brandIdsString = paramJSONObject.getString("brands");
			localStore.distance = paramJSONObject.getDouble("distance");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return localStore;
	}

	public ContentValues getContentValues() {
		String str = stringFromList(this.brandIds);
		ContentValues localContentValues = new ContentValues();
		localContentValues.put(
				ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_TRUE_ID, id);
		localContentValues.put(
				ConstantsHelper.GLOBAL_DATABASE_COLUMN_NAME_FOR_UPDATED_AT,
				updatedAt);
		localContentValues.put("name", this.name);
		localContentValues.put("address", this.address);
		localContentValues.put("address_url", this.addressUrl);
		localContentValues.put("latitude", Double.valueOf(this.latitude));
		localContentValues.put("longitude", Double.valueOf(this.longitude));
		localContentValues.put("brand_ids", str);
		return localContentValues;
	}

	public String stringFromList(List<Integer> ids) {
		String idsAsString = "(";
		for (int i = 0; i < ids.size(); i++) {
			Integer idAtIndex = ids.get(i);
			idsAsString = idsAsString.concat(Integer.toString(idAtIndex));
			if (i != ids.size() - 1)
				idsAsString = idsAsString
						.concat(ConstantsHelper.SEPARATOR_FOR_IDS_AS_STRING);
		}
		idsAsString = idsAsString.concat(")");
		return idsAsString;
	}
}
