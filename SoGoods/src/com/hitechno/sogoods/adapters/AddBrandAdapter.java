package com.hitechno.sogoods.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.AddBrandActivity;
import com.hitechno.sogoods.api.APIProfile;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.util.Configuration;
import com.hitechno.sogoods.util.MySharePreference;

@SuppressLint("NewApi")
public class AddBrandAdapter extends ArrayAdapter<Brand> implements SectionIndexer {
	private Configuration config;
	  private String apiKey;
	  private APIProfile apiProfile;
	  private ArrayList<Brand> filteredBrands;
	  private Context ctxt;
	private String mSections = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public AddBrandAdapter(Context context,
			List<Brand> objects) {
		super(context,R.layout.item_addbrand, objects);
		 apiKey = MySharePreference.getApiKey(context);

	      apiProfile = new APIProfile();
		    config = Configuration.getInstance(context);
		    filteredBrands=(ArrayList<Brand>) objects;
		    ctxt=context;
	}
	
@Override
public View getView(final int position, View convertView, ViewGroup parent) {
ViewHolder holder;
Brand b= filteredBrands.get(position);
if (convertView == null) {
	LayoutInflater inflater = (LayoutInflater) ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	holder = new ViewHolder();
	convertView = inflater.inflate(R.layout.item_addbrand,parent, false);
	holder = new ViewHolder();
	holder.sec = (TextView)convertView.findViewById(R.id.item_addbrand_section);
	holder.Title = (TextView)convertView.findViewById(R.id.item_addbrand_brand_name);
	holder.cats = (TextView)convertView.findViewById(R.id.item_addbrand_brand_des);
	holder.follow = ((TextView)convertView.findViewById(R.id.item_addbrand_follow_button));
	convertView.setTag(holder);
	} else
holder = (ViewHolder) convertView.getTag();
RelativeLayout rv = ((RelativeLayout)convertView.findViewById(R.id.item_addbrand_follow_layout));

if(position!=0)
{if(filteredBrands.get(position).name.substring(0, 1).toUpperCase(Locale.US).equals(filteredBrands.get(position-1).name.substring(0, 1).toUpperCase(Locale.US)))
	holder.sec.setVisibility(View.GONE);
else
	holder.sec.setVisibility(View.VISIBLE);
}
else
	holder.sec.setVisibility(View.VISIBLE);	
holder.sec.setText(b.name.substring(0, 1).toUpperCase(Locale.US));
holder.cats.setText(b.listCategory);
holder.Title.setText(b.name);


 if (b.isFollowed)
    {
	 holder.follow.setText(ctxt.getResources().getString(R.string.following));
	 holder.follow.setBackgroundResource(R.drawable.button_unfollow);
    }
    else
    {
    	holder.follow.setText(ctxt.getResources().getString(R.string.follow));
    	holder.follow.setBackgroundResource(R.drawable.button_follow);
  
    }
    

 holder.follow.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		FollowBrandAsync localFollowBrandAsync = new FollowBrandAsync();
        Executor localExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        Integer[] arrayOfInteger = new Integer[1];
        arrayOfInteger[0] = Integer.valueOf(position);
        localFollowBrandAsync.executeOnExecutor(localExecutor, arrayOfInteger);
		
	}
});


return convertView;
}

@Override
public Brand getItem(int position) {
return filteredBrands.get(position);
}
@Override
public long getItemId(int position) {
return position;
}
@Override
public int getCount() {
if(filteredBrands!=null)
	return filteredBrands.size();
else
	return 0;
}

	@Override
	public int getPositionForSection(int section) {
		String text = null;
		for (int j = 0; j < getCount(); j++) {
			if (section == 0) {
				for (int k = 0; k <= 9; k++) {
					
					try {
						text = filteredBrands.get(j).name;
					} catch (Exception e) {
					}
					if (text == null)
						return 0;
					else if (String.valueOf(text.charAt(0)).toLowerCase().equals(String.valueOf(String.valueOf(k)).toString().toLowerCase()))
						return j;
				}
			} else {
				
				try {
					text = filteredBrands.get(j).name;
				} catch (Exception e) {
				}
				if (text == null)
					return 0;
				else if (String.valueOf(text.charAt(0)).toLowerCase().equals(String.valueOf(mSections.charAt(section)).toString().toLowerCase())) {
					return j;
				}
			}
		}
		return 0;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	@Override
	public Object[] getSections() {
		String[] sections = new String[mSections.length()];
		for (int i = 0; i < mSections.length(); i++)
			sections[i] = String.valueOf(mSections.charAt(i));
		return sections;
	}
    private class FollowBrandAsync
    extends AsyncTask<Integer, Void, Boolean>
  {
    private boolean kind;
    private int position;
    
    private FollowBrandAsync() {}
    
    protected Boolean doInBackground(Integer... paramVarArgs)
    {
      this.position = paramVarArgs[0].intValue();
      Brand localBrand = filteredBrands.get(position);
      this.kind = localBrand.isFollowed;
      if (this.kind) {
        return Boolean.valueOf(apiProfile.deleteBrand(localBrand.brandID, apiKey));
      }
      return Boolean.valueOf(apiProfile.addBrand(localBrand.brandID, apiKey));
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      if (paramBoolean.booleanValue()) {
        if (!this.kind) {
        	((Brand)filteredBrands.get(this.position)).isFollowed = true;
        }
        else
        	((Brand)filteredBrands.get(this.position)).isFollowed = false;
      }
      notifyDataSetChanged();
    }
  }
     class ViewHolder {
    	TextView Title;
    	TextView cats;
    	TextView sec;
    	TextView follow;
    	}
}
