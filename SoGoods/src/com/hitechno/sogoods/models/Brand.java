package com.hitechno.sogoods.models;

import android.content.ContentValues;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Brand
  implements Comparable<String>
{

  public static Comparator<Brand> nameBrandComparator =new Comparator<Brand>() {
		
	@Override
	public int compare(Brand lhs, Brand rhs) {
		// TODO Auto-generated method stub
		return lhs.name.toUpperCase().compareTo(rhs.name.toUpperCase());
	}
};
  public int brandID;
  public int categoryId;
  public int catelogsNumber;
  public int cityID;
  public String cityName;
  public int currentFans;
  public String distance;
  public int id;
  public int isFan;
  public boolean isFollowed;
  public String latutide;
  public String listCategory;
  public String longtitude;
  public ArrayList<Integer> lstCategories;
  public ArrayList<String> lstUrl;
  public String name;
  public int newsNumber;
  public int numberOfProfiles;
  public int productsNumber;
  public ArrayList<Integer> profileIds;
  public int status;
  public ArrayList<Integer> storeIds;
  public String storeIdsString;
  public String storesNumber;
  public int trueId;
  public String updatedAt;
  public String url;
  
  public static Brand initUnitFromCursor(Cursor paramCursor)
  {
    Brand localBrand = new Brand();
    localBrand.brandID = paramCursor.getInt(paramCursor.getColumnIndex("brand_id"));
    localBrand.name = paramCursor.getString(paramCursor.getColumnIndex("brand_name"));
    localBrand.url = paramCursor.getString(paramCursor.getColumnIndex("brand_logo_square"));
    localBrand.status = paramCursor.getInt(paramCursor.getColumnIndex("brand_status"));
    return localBrand;
  }
  
  public int compareTo(String paramString)
  {
    return 0;
  }
  
  public ContentValues getContentValues()
  {
    String str1 = stringFromList(this.profileIds);
    String str2 = stringFromList(this.storeIds);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("true_id", Integer.valueOf(this.id));
    localContentValues.put("updated_at", this.updatedAt);
    localContentValues.put("name", this.name);
    localContentValues.put("url", this.url);
    localContentValues.put("number_of_fans", Integer.valueOf(this.numberOfProfiles));
    localContentValues.put("profile_ids", str1);
    localContentValues.put("is_fan", Integer.valueOf(this.isFan));
    localContentValues.put("category_id", Integer.valueOf(this.categoryId));
    localContentValues.put("store_ids", str2);
    return localContentValues;
  }
  
  @Override
public String toString() {
	return "Brand [brandID=" + brandID + ", categoryId=" + categoryId
			+ ", catelogsNumber=" + catelogsNumber + ", currentFans="
			+ currentFans + ", id=" + id + ", isFan=" + isFan + ", isFollowed="
			+ isFollowed + ", latutide=" + latutide + ", listCategory="
			+ listCategory + ", longtitude=" + longtitude + ", lstCategories="
			+ lstCategories + ", lstUrl=" + lstUrl + ", name=" + name
			+ ", newsNumber=" + newsNumber + ", numberOfProfiles="
			+ numberOfProfiles + ", productsNumber=" + productsNumber
			+ ", profileIds=" + profileIds + ", status=" + status
			+ ", storeIds=" + storeIds + ", storeIdsString=" + storeIdsString
			+ ", storesNumber=" + storesNumber + ", trueId=" + trueId + "]";
}

public ContentValues getContentValuesForSafeUpdate()
  {
    String str = stringFromList(this.profileIds);
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("true_id", Integer.valueOf(this.id));
    localContentValues.put("number_of_fans", Integer.valueOf(this.numberOfProfiles));
    localContentValues.put("profile_ids", str);
    localContentValues.put("is_fan", Integer.valueOf(this.isFan));
    return localContentValues;
  }
  
  public String stringFromList(List<Integer> paramList)
  {
    String str = "(";
    for (int i = 0;; i++)
    {
      if (i >= paramList.size()) {
        return str.concat(")");
      }
      str = str.concat(Integer.toString(((Integer)paramList.get(i)).intValue()));
      if (i != -1 + paramList.size()) {
        str = str.concat(",");
      }
    }
  }
}

