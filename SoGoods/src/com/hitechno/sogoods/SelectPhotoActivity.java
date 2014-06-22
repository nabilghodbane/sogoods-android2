package com.hitechno.sogoods;


import com.hitechno.sogoods.R;
import com.hitechno.sogoods.helpers.ConstantsHelper;
import com.hitechno.sogoods.views.FuturaToggleButton;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SelectPhotoActivity extends FragmentActivity implements OnCheckedChangeListener{
	ImageView bttBackDiary, imageViewCut, imageViewCamera;
	EditText editSearchbrand;

//	RadioGroup radioGroup;
//	FuturaToggleButton bttGoogle;
//	FuturaToggleButton bttgallery;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.select_photos_fragment);
	
		init();
	}

	private void init() {
		bttBackDiary = (ImageView)findViewById(R.id.bttBackDiary);
		imageViewCut = (ImageView)findViewById(R.id.imageViewCut);
		imageViewCamera = (ImageView)findViewById(R.id.imageViewCamera);
		editSearchbrand = (EditText)findViewById(R.id.editSearchbrand);
//		radioGroup = (RadioGroup)findViewById(R.id.filters_buttons);
//		bttGoogle = (FuturaToggleButton)findViewById(R.id.btt_google);
//		bttgallery = (FuturaToggleButton)findViewById(R.id.btt_gallery);

//		radioGroup.setOnCheckedChangeListener(this);
//		((RadioGroup) radioGroup).check(R.id.btt_google);
		

		/*if(AddProductActivity.txtBrand.getText().toString().equals(getResources().getString(R.string.brandName))){
			editSearchbrand.setText("");
		}else{
			editSearchbrand.setText(AddProductActivity.txtBrand.getText().toString());
		}*/

		/*editSearchbrand.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				imageViewCut.setVisibility(View.VISIBLE);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});*/
	}

	// process cut button
	public void onclickCutButton(View view){
		editSearchbrand.setText("");
		imageViewCut.setVisibility(View.GONE);
	}
	public void processBackButton(View view){
		onBackPressed();
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
		ConstantsHelper.animationPro(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
//		FragmentManager fragmentManager = getSupportFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager
//				.beginTransaction();
//		for (int i = 0; i < group.getChildCount(); i++) {
//			View view = group.getChildAt(i);
//			((ToggleButton) view).setChecked(view.getId() == checkedId);
//			if (view.getId() == checkedId) {
//				switch (view.getId()) {
//				case R.id.btt_google:
//					bttGoogle.setVisibility(View.VISIBLE);
//					bttGoogle.setBackgroundResource(R.drawable.right_menu_switch);
//
//					bttgallery.setVisibility(View.VISIBLE);
//					bttgallery.setBackgroundDrawable(null);
//					bttgallery.setText(getResources().getString(R.string.gallery));
//					break;
//				case R.id.btt_gallery:
//					bttgallery.setVisibility(View.VISIBLE);
//					bttgallery.setBackgroundResource(R.drawable.right_menu_switch);
//
//					bttGoogle.setVisibility(View.VISIBLE);
//					bttGoogle.setBackgroundDrawable(null);
//					bttGoogle.setText("google");
//					LoadGallaryPhotosFragment gallarFragment = new LoadGallaryPhotosFragment();
//					fragmentTransaction.replace(R.id.product_detail_container, gallarFragment);
//					fragmentTransaction.commit();
//					break;
//				default:
//					break;
//				}
//			}
//		}
	}
	public void onPrductFilterClicked(View view) {
		RadioGroup radioGroup = (RadioGroup) view.getParent();
		radioGroup.check(view.getId());
	}

	public void processOnclickCamera(View view){
		/*Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File file = getOutputPhotoFile();
		fileUri = Uri.fromFile(getOutputPhotoFile());
		i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
		startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQ );*/
	}


}
