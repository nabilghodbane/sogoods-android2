package com.hitechno.sogoods;

import com.hitechno.sogoods.constant.ConstantsHelper;
import com.hitechno.sogoods.fragments.SearchCityNameFragment;
import com.hitechno.sogoods.model.LocationMin;
import com.hitechno.sogoods.util.GoogleGeoLocation;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

public class StoreLocatorAcivity extends FragmentActivity {
	private int m_CategoryID;
	private LocateTask m_Locate = null;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_brand_by_category);
		Intent startingIntent = getIntent();
		m_CategoryID = startingIntent.getIntExtra(ConstantsHelper.Categories.CATEGORIES_ID, 0);
		TextView txt = (TextView)findViewById(R.id.brandbycate_title);
		txt.setText(startingIntent.getStringExtra(ConstantsHelper.Categories.CATEGORIES_NAME));
		
		m_Locate = new LocateCurrent();
		m_Locate.execute();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBack:
		case R.id.txtBack:
			finish();
			break;
		case R.id.brandbycate_search_input:
			FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
			SearchCityNameFragment localSearchCityNameFragment = new SearchCityNameFragment();
			Bundle localBundle = new Bundle();
			localBundle.putInt("source", 2);
			localSearchCityNameFragment.setArguments(localBundle);
			localFragmentTransaction.replace(R.id.rootLayout, localSearchCityNameFragment, "TAG_CITY");
			localFragmentTransaction.commit();
			break;
		}
	}
	
	private abstract class LocateTask extends AsyncTask<Void, Void, LocationMin> {
		private boolean m_Aborted = false;
		
		protected abstract LocationMin locate();
		
		@Override
		protected LocationMin doInBackground(Void... params) {
			return locate();
		}

		@Override
		protected void onPostExecute(LocationMin result) {
			if (!m_Aborted) {
				
			}
		}
		
		public void abort() {
			m_Aborted = true;
		}
	}
	
	private class LocateCurrent extends LocateTask {
		@Override
		protected LocationMin locate() {
			LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
			String provider = manager.getBestProvider(new Criteria(), true);
			Location loc = manager.getLastKnownLocation(provider);
			GoogleGeoLocation.ReverseGeoLocationResult[] addresses = GoogleGeoLocation.reverseLookup(loc.getLatitude(),
					loc.getLongitude());
			LocationMin result = null;
			if (addresses != null) {
				String location = addresses[0].getComponentForType(GoogleGeoLocation.AddressComponent.TYPE_LOCALITY).getLongName();
				String adminArea = addresses[0].getComponentForType(GoogleGeoLocation.AddressComponent.TYPE_ADMIN_AREA).getLongName();
				String country = addresses[0].getComponentForType(GoogleGeoLocation.AddressComponent.TYPE_COUNTRY).getLongName();
				result = new LocationMin(loc.getLatitude(), loc.getLongitude(), location + ", " + adminArea + ", " + country);
			}
			return result;
		}
	}
	
	private class LocateAddress extends LocateTask {
		private String m_Address;
		
		public LocateAddress(String address) {
			m_Address = address;
		}
		
		@Override
		protected LocationMin locate() {
			return GoogleGeoLocation.lookup(m_Address);
		}
	}
}
