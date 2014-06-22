package com.hitechno.sogoods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.hitechno.sogoods.adapters.AddBrandAdapter;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.databases.BrandDBAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Category;
import com.hitechno.sogoods.models.Profile;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.IndexableListView;
import com.hitechno.sogoods.util.MySharePreference;
import com.hitechno.sogoods.views.FuturaTextView;
import com.mttam.toollibrary.tools.Tools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
public class AddBrandActivity extends Activity{
	private IndexableListView mListView;
	private APIProfile apiProfile;
	private String apiKey;
	  private BrandDBAdapter brandDB;
	  private CategoryDBAdapter categoryDB;
	  private ArrayList<Brand> originalBrands;
	  private ArrayList<Brand> filteredBrands;
	  private EditText searchInput;
	  private ProgressBar progressBar;
	  AddBrandAdapter adapter;
	  Context context;
	  FuturaTextView Okbtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_addbrand);
	    brandDB=DBManager.getBrandDBAdapter(this);
	     Okbtn=(FuturaTextView)findViewById(R.id.addbrand_button_OK); 
	    searchInput=(EditText)findViewById(R.id.addbrand_search_input);
	    progressBar =(ProgressBar)findViewById(R.id.pbLoading);
	    mListView = (IndexableListView) findViewById(R.id.listview);
	      categoryDB=DBManager.getCategoryDBAdapter(this);
	      filteredBrands= new ArrayList<Brand>();
	      originalBrands=  brandDB.getAllBrands();
	      apiProfile = new APIProfile();
	      apiKey = MySharePreference.getApiKey(context);
	      Collections.sort(originalBrands, Brand.nameBrandComparator);
	      context=this;
	       for(int i=0;i<originalBrands.size();i++)
	      { 
	    	ArrayList<Category> cats= categoryDB.getAllCategoriesWithBrand(originalBrands.get(i).brandID);
	    	  String ls = ""; 
	    	  for(int j=0;j<cats.size();j++)
	    	  {
	    		if(!ls.equals("")) 
	    		ls=ls+","+cats.get(j).title;
	    		else
	    		ls=cats.get(j).title;
	    	  }
	    	  originalBrands.get(i).listCategory=ls; 
	    	  filteredBrands.add(originalBrands.get(i));
	      }
	       mListView.setVisibility(View.GONE);
	       adapter = new AddBrandAdapter(AddBrandActivity.this, filteredBrands);
	       mListView.setAdapter(adapter);
	       mListView.setFastScrollEnabled(true);
	      new LoadUserProfileAsync().execute(MySharePreference.getProfileID(context));
	       searchInput.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if(filteredBrands!=null)
						filteredBrands.clear();
				  adapter =new AddBrandAdapter(context,search(s.toString()));
					  mListView.setAdapter(adapter);
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
				}
				
				@Override
				public void afterTextChanged(Editable s) {
				}
			});
	       Okbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
    }
    
    
    private ArrayList<Brand> search(String paramString)
    {
  	  Iterator<Brand> localIterator = originalBrands.iterator();
      while (localIterator.hasNext()) {
      	  Brand localBrand = (Brand)localIterator.next();
      	  if (localBrand.name.toLowerCase().contains(paramString.toLowerCase())) {
      		filteredBrands.add(localBrand);
      	      }
        }
        return filteredBrands;
    }

    private class LoadUserProfileAsync
    extends AsyncTask<Integer, Void, String>
  {
    private LoadUserProfileAsync() {}
    
    protected String doInBackground(Integer... paramVarArgs)
    {
      try
      {
        String str = new APIProfile().loadContentProfileUser(AddBrandActivity.this.context, paramVarArgs[0].intValue(), apiKey);
        return str;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
    }
    
    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      if (paramString != null) {}
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString).getJSONObject("profil");
        MySharePreference.saveProfileContent(context, localJSONObject.toString());
       progressBar.setVisibility(8);
       if (!Tools.isEmpty(localJSONObject.toString())) {
			try {
				Profile profile = Profile.initFromJson(new JSONObject(localJSONObject.toString()),
						Configuration.getInstance(context).typePhone);

				if (profile != null) {
					String[] BrandsFollowed=profile.brands.split(",");
					for (Brand b :filteredBrands)
					{int i=0;
					while(i<BrandsFollowed.length && !BrandsFollowed[i].equals(""+b.brandID))
						i++;
					if(i<BrandsFollowed.length)
						b.isFollowed=true;
					}
					mListView.refreshDrawableState();
					mListView.setVisibility(View.VISIBLE);
				
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
       }

      }
      catch (Exception localException)
      {
          localException.printStackTrace();
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
     progressBar.setVisibility(0);
    }
  }

}
