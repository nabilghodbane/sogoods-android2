package com.hitechno.sogoods.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.hitechno.sogoods.HomeActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.FilterBrandAdapter;
import com.hitechno.sogoods.databases.BrandDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.util.Debugs;
import com.hitechno.sogoods.util.MySharePreference;
import com.mttam.toollibrary.tools.Tools;

public class MenuRightFilterBrandFragment extends Fragment {

	private ListView brandListView;
	private TextView txtNoresult;

	private GestureDetector mGestureDetector;

	// x and y coordinates within our side index
	private static float sideIndexX;
	private static float sideIndexY;

	// height of side index
	private int sideIndexHeight;

	// number of items in the side index
	private int indexListSize;

	// list with items for side index
	private ArrayList<Object[]> indexList = null;
	private List<String> lststr;
	private ArrayList<Brand> brands;
	private View rootView;
	private LinearLayout sideIndex;

	private BrandDBAdapter brandDB;

	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		brandDB = DBManager.getBrandDBAdapter(getActivity());
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_menu_right_filter,
				container, false);
		sideIndex = (LinearLayout) rootView.findViewById(R.id.sideIndex);
		sideIndex.setVisibility(View.VISIBLE);

		brandListView = (ListView) rootView
				.findViewById(R.id.filter_product_list);
		txtNoresult = (TextView) rootView.findViewById(R.id.txtNoresult);

		loadBrandList();

		brandListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Brand brand = brands.get(arg2);
				int idBrand = brand.brandID;
				Debugs.show("d", "MenuRightFilterBrandFragment",
						"Filter with brand " + brand.name);
				MySharePreference.saveBrandFilter(context, idBrand);

				closeRightMenu();
				ProductsFragment.getInstance().reloadDataFilter(brand.name);

			}
		});

		return rootView;
	}

	public void closeRightMenu() {
		HomeActivity a = (HomeActivity) getActivity();
		a.closeRightMenu();
	}

	public void loadBrandList() {
		try {
			brands = brandDB.getAllBrands();
			if (brands != null && brands.size() > 0) {
				txtNoresult.setVisibility(View.GONE);
				FilterBrandAdapter filterDetailAdapter = new FilterBrandAdapter(
						getActivity(), R.layout.item_menuright_category_filter,
						brands);
				brandListView.setAdapter(filterDetailAdapter);
				prepareData(brands);
				Handler handler = new Handler();
				handler.post(new Runnable() {

					@Override
					public void run() {
						onWindowFocusChanged(true, rootView);
					}
				});
			} else {
				txtNoresult.setVisibility(View.VISIBLE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void prepareData(ArrayList<Brand> arr) {
		lststr = new ArrayList<String>();
		for (Brand brd : arr) {
			if (!Tools.isEmpty(brd.name))
				lststr.add(brd.name);
		}
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (mGestureDetector.onTouchEvent(event)) {
			return true;
		} else {
			return false;
		}
	}

	private ArrayList<Object[]> createIndex(List<String> strArr) {
		ArrayList<Object[]> tmpIndexList = new ArrayList<Object[]>();
		Object[] tmpIndexItem = null;

		int tmpPos = 0;
		String tmpLetter = "";
		String currentLetter = null;
		String strItem = null;

		for (int j = 0; j < strArr.size(); j++) {
			strItem = strArr.get(j);

			currentLetter = strItem.substring(0, 1);

			// every time new letters comes
			// save it to index list
			if (!currentLetter.equalsIgnoreCase(tmpLetter)) {
				tmpIndexItem = new Object[3];
				tmpIndexItem[0] = tmpLetter;
				tmpIndexItem[1] = tmpPos - 1;
				tmpIndexItem[2] = j - 1;

				tmpLetter = currentLetter;
				tmpPos = j + 1;

				tmpIndexList.add(tmpIndexItem);
			}
		}

		// save also last letter
		tmpIndexItem = new Object[3];
		tmpIndexItem[0] = tmpLetter;
		tmpIndexItem[1] = tmpPos - 1;
		tmpIndexItem[2] = strArr.size() - 1;
		tmpIndexList.add(tmpIndexItem);

		// and remove first temporary empty entry
		if (tmpIndexList != null && tmpIndexList.size() > 0) {
			tmpIndexList.remove(0);
		}

		return tmpIndexList;
	}

	public void onWindowFocusChanged(boolean hasFocus, View view) {

		sideIndexHeight = sideIndex.getHeight();
		sideIndex.removeAllViews();

		// TextView for every visible item
		TextView tmpTV = null;
		// we'll create the index list
		indexList = createIndex(lststr);

		// number of items in the index List
		indexListSize = indexList.size();
		// maximal number of item, which could be displayed

		int indexMaxSize = (int) Math.floor(sideIndexHeight / 20);

		int tmpIndexListSize = indexListSize;

		// handling that case when indexListSize > indexMaxSize
		while (tmpIndexListSize > indexMaxSize) {
			tmpIndexListSize = tmpIndexListSize / 2;
		}

		// computing delta (only a part of items will be displayed to save a
		// place)
		double delta = indexListSize / tmpIndexListSize;

		String tmpLetter = null;
		Object[] tmpIndexItem = null;

		// show every m-th letter
		for (double i = 1; i <= indexListSize; i = i + delta) {
			tmpIndexItem = indexList.get((int) i - 1);
			tmpLetter = tmpIndexItem[0].toString();
			tmpTV = new TextView(getActivity());
			tmpTV.setText(tmpLetter);
			tmpTV.setTextColor(getResources().getColor(R.color.button1));
			tmpTV.setGravity(Gravity.CENTER);
			tmpTV.setTextSize(18);
			LayoutParams params = new LayoutParams(
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
					android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1);
			tmpTV.setLayoutParams(params);
			sideIndex.addView(tmpTV);

		}

		// and set a touch listener for it
		sideIndex.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// now you know coordinates of touch
				sideIndexX = event.getX();
				sideIndexY = event.getY();

				// and can display a proper item it country list
				displayListItem();

				return false;
			}
		});
	}

	class SideIndexGestureListener extends
			GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// we know already coordinates of first touch
			// we know as well a scroll distance
			sideIndexX = sideIndexX - distanceX;
			sideIndexY = sideIndexY - distanceY;

			// when the user scrolls within our side index
			// we can show for every position in it a proper
			// item in the country list
			if (sideIndexX >= 0 && sideIndexY >= 0) {
				displayListItem();
			}

			return super.onScroll(e1, e2, distanceX, distanceY);
		}
	}

	public void displayListItem() {
		// compute number of pixels for every side index item
		double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

		// compute the item index for given event position belongs to
		int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

		// compute minimal position for the item in the list
		int minPosition = (int) (itemPosition * pixelPerIndexItem);

		// get the item (we can do it since we know item index)
		Object[] indexItem = indexList.get(itemPosition);

		// and compute the proper item in the country list
		int indexMin = Integer.parseInt(indexItem[1].toString());
		int indexMax = Integer.parseInt(indexItem[2].toString());
		int indexDelta = Math.max(1, indexMax - indexMin);

		double pixelPerSubitem = pixelPerIndexItem / indexDelta;
		int subitemPosition = (int) (indexMin + (sideIndexY - minPosition)
				/ pixelPerSubitem);

		brandListView.setSelection(subitemPosition);
	}
}
