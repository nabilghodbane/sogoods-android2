package com.hitechno.sogoods.api;

import android.util.Log;
import android.util.SparseArray;

import com.mttam.toollibrary.tools.Tools;
import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.constant.ConstantsHelper.Brands;
import com.hitechno.sogoods.constant.ConstantsHelper.Categories;
import com.hitechno.sogoods.models.Brand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIBrand
{
  public ArrayList<Brand> getAllCityName(String paramString)
  {
	  ArrayList<Brand> localArrayList = new ArrayList<Brand>();
    try
    {
      String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/city/search.json", ConstantsHelper.Categories.getListSearchCityNamePostParams(paramString, Locale.getDefault().getLanguage()));
     Log.w("cities",""+str);
      if (Tools.isEmpty(str)) {
        return localArrayList;
      }
      JSONObject localJSONObject1 = new JSONObject(str);
      if (localJSONObject1.has("cities"))
      {
        JSONArray localJSONArray = new JSONArray(localJSONObject1.getString("cities"));
        for (int i = 0; i < localJSONArray.length(); i++)
        {
          JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
          Brand localBrand = new Brand();
          localBrand.cityID = localJSONObject2.getInt("id");
          localBrand.cityName = localJSONObject2.getString("name");
          localArrayList.add(localBrand);
        }
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
	return localArrayList;
  }
  
  public SparseArray<String> getCategoriesDetailByID(int paramInt, double paramDouble1, double paramDouble2, String paramString1, String paramString2)
  {
	  SparseArray<String> localSparseArray = new SparseArray<String>();
	   try
	    {
	      JSONObject localJSONObject1 = new JSONObject(Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/brands/get_by_category_by_location.json", ConstantsHelper.Brands.getDetailCatePostToParams(paramString1, paramInt, paramString2, 500, paramDouble1, paramDouble2)));
	      if (localJSONObject1.getBoolean("success"))
	      {
	        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("brands");
	        Iterator localIterator = localJSONObject2.keys();
	        for (;;)
	        {
	          if (!localIterator.hasNext()) {
	            return localSparseArray;
	          }
	          String str = (String)localIterator.next();
	          if ((localJSONObject2.get(str) instanceof String)) {
	            localSparseArray.append(Integer.parseInt(str), (String)localJSONObject2.get(str));
	          }
	        }
	      }
	    }
	    catch (JSONException localJSONException)
	    {
	      localJSONException.printStackTrace();
	    }
    return localSparseArray;
  }
  
  public ArrayList<String> newLoadFan(int paramInt, String paramString)
  {
	  ArrayList<String> localArrayList = new ArrayList<String>();
    
    String str = Connectivity.loadContentFromAPI("http://webservices.preprod.sogoods.hitechno-ltd.com/v.0.1/profile/get_brands.json", ConstantsHelper.Brands.new_loadFansByProfile(paramInt, paramString, Locale.getDefault().getLanguage()));
    try
    {
      JSONArray localJSONArray = new JSONArray(new JSONObject(str).getString("brands"));
      for (int i = 0;; i++)
      {
        if (i >= localJSONArray.length()) {
          return localArrayList;
        }
        localArrayList.add(localJSONArray.get(i).toString());
      }
      
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
	return localArrayList;
  }
}
