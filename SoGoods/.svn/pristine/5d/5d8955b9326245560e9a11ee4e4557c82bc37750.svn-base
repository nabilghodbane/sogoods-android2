package com.hitechno.sogoods;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.models.Store;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreMenuLeftActivity extends FragmentActivity{
	TextView store_name;
	ImageView imgStore,bttBackDiary;
	Button store_address,store_address_phone,store_address_url;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.store_searchmenuleft_activity);
		init();
	}
	
	public void init(){
		Bundle bundle = getIntent().getExtras();
		Store store = (Store) bundle.getSerializable("store");
		
		store_name = (TextView)findViewById(R.id.store_name);
		imgStore = (ImageView)findViewById(R.id.imgStore);
		bttBackDiary = (ImageView)findViewById(R.id.bttBackDiary);
		store_address = (Button)findViewById(R.id.store_address);
		store_address_phone = (Button)findViewById(R.id.store_address_phone);
		store_address_url = (Button)findViewById(R.id.store_address_url);
		
		store_name.setText(store.name);
		store_name.setBackgroundColor(Color.TRANSPARENT);
		String htmladd = store.address+"<br/>"+store.cityName;
		store_address.setText(Html.fromHtml(htmladd), TextView.BufferType.SPANNABLE);
		store_address_phone.setText(store.phone);
		store_address_url.setText(store.addressUrl);
		
		bttBackDiary.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ConstantsHelper.animationPro(StoreMenuLeftActivity.this);
	}

}
