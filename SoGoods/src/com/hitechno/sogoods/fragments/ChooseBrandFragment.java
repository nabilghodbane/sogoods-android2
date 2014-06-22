package com.hitechno.sogoods.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hitechno.sogoods.AddProductActivity;
import com.hitechno.sogoods.R;
import com.hitechno.sogoods.adapters.FilterBrandAdapter2;
import com.hitechno.sogoods.databases.BrandDBAdapter;
import com.hitechno.sogoods.databases.CategoryDBAdapter;
import com.hitechno.sogoods.databases.DBManager;
import com.hitechno.sogoods.models.Brand;
import com.hitechno.sogoods.models.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ChooseBrandFragment
  extends Fragment
{
  private static float sideIndexX;
  private static float sideIndexY;
  private TextView backButton;
  private BrandDBAdapter brandDB;
  private CategoryDBAdapter categoryDB;
  private Context context;
  private EditText editSearchbrand;
  FilterBrandAdapter2 filterDetailAdapter;
  ImageView imageViewCut;
  ImageView imageViewSearch;
  ArrayList<Object[]> indexList;
  private int indexListSize;
  private TextView lbNameHeader;
  private ArrayList<Brand> listBrand;
  public ArrayList<Brand> listSearch;
  ArrayList<Brand> lstBrandsSearch;
  private ListView lstViewBrand;
  ArrayList<String> lststr;
  private GestureDetector mGestureDetector;
  ProgressBar progressBar;
  View rootView;
  LinearLayout sideIndex;
  private int sideIndexHeight;
  private TextView txtNoResult;
  
  private ArrayList<Object[]> createIndex(ArrayList<String> paramArrayList)
  {
    ArrayList<Object[]> localArrayList = new ArrayList<Object[]>();
    int i = 0;
    Object localObject = "";
    for (int j = 0;; j++)
    {
      if (j >= paramArrayList.size())
      {
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = localObject;
        arrayOfObject2[1] = Integer.valueOf(i - 1);
        arrayOfObject2[2] = Integer.valueOf(-1 + paramArrayList.size());
        localArrayList.add(arrayOfObject2);
        if ((localArrayList != null) && (localArrayList.size() > 0)) {
          localArrayList.remove(0);
        }
        return localArrayList;
      }
      String str = ((String)paramArrayList.get(j)).substring(0, 1);
      if (!str.equalsIgnoreCase((String)localObject))
      {
        Object[] arrayOfObject1 = new Object[3];
        arrayOfObject1[0] = localObject;
        arrayOfObject1[1] = Integer.valueOf(i - 1);
        arrayOfObject1[2] = Integer.valueOf(j - 1);
        localObject = str;
        i = j + 1;
        localArrayList.add(arrayOfObject1);
      }
    }
  }
  
  public void displayListItem()
  {
    double d1 = this.sideIndexHeight / this.indexListSize;
    int i = (int)(sideIndexY / d1);
    int j = (int)(d1 * i);
    Object[] arrayOfObject = (Object[])this.indexList.get(i);
    int k = Integer.parseInt(arrayOfObject[1].toString());
    double d2 = d1 / Math.max(1, Integer.parseInt(arrayOfObject[2].toString()) - k);
    int m = (int)(k + (sideIndexY - j) / d2);
    this.lstViewBrand.setSelection(m);
  }
  
  void init(View paramView)
  {
    final AddProductActivity localAddProductActivity = (AddProductActivity)getActivity();
    this.lbNameHeader = ((TextView)paramView.findViewById(R.id.lbNameHeader));
    this.lstViewBrand = ((ListView)paramView.findViewById(R.id.lstViewBrand));
    this.txtNoResult = ((TextView)paramView.findViewById(R.id.txtNoResult));
    this.backButton = ((TextView)paramView.findViewById(R.id.profile_header_menu_button));
    this.editSearchbrand = ((EditText)paramView.findViewById(R.id.editSearchbrand));
    this.progressBar = ((ProgressBar)paramView.findViewById(R.id.pbLoading));
    this.imageViewSearch = ((ImageView)paramView.findViewById(R.id.imageViewSearch));
    this.imageViewCut = ((ImageView)paramView.findViewById(R.id.imageViewCut));
    this.sideIndex = ((LinearLayout)paramView.findViewById(R.id.sideIndex));
    if (localAddProductActivity.categoryLabel.getText().toString().equals(getResources().getString(R.string.addproduct_hint2)))
    {
      this.lbNameHeader.setText(getResources().getString(R.string.addproduct_hint1));
     
    }
    this.backButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((AddProductActivity)ChooseBrandFragment.this.getActivity()).onBackPressed();
      }
    });
    this.lstViewBrand.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        Brand localBrand = (Brand)((Adapter)paramAnonymousAdapterView.getAdapter()).getItem(paramAnonymousInt);
        localAddProductActivity.brandLabel.setText(localBrand.name);
        localAddProductActivity.BRAND_ID = localBrand.brandID;
        localAddProductActivity.onBackPressed();
      }
    });
    //TODO:
//    for (;;)
//    {
      try
      {
    	  Iterator<String> localIterator;
          String str;
          Brand localBrand;
       
 //         this.lbNameHeader.setText(getResources().getString(2131165394) + " " + localAddProductActivity.categoryLabel.getText());
          localIterator = this.categoryDB.getCategoryByIdAndStatus(localAddProductActivity.CATEGORY_ID, 1).initArrayBrandId().iterator();
          while (localIterator.hasNext()) {
        	  str = (String)localIterator.next();
              localBrand = this.brandDB.select(Integer.parseInt(str));
              if (localBrand != null) {
                this.listBrand.add(localBrand);
              }
        	}
       if ((this.listBrand == null) || (this.listBrand.size() <= 0)) {
        	 this.txtNoResult.setVisibility(0);
        }
        else
        {
        this.txtNoResult.setVisibility(8);
        this.filterDetailAdapter = new FilterBrandAdapter2(this.context, R.layout.category_and_brand_grid_item, this.listBrand);
        this.lstViewBrand.setAdapter(this.filterDetailAdapter);
        prepareData(this.listBrand);
        onWindowFocusChanged(true, this.rootView);
        }
      }
      catch (Exception localException)
      {
    	   localException.printStackTrace();
      }
      
      this.progressBar.setVisibility(8);
      
      
//TODO: A verifier       
      this.editSearchbrand.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          String str = paramAnonymousCharSequence.toString();
          if (str.length() == 0)
          {
            ChooseBrandFragment.this.imageViewCut.setVisibility(8);
           
          }
          listSearch = new ArrayList<Brand>();
          for (int i = 0;i<listBrand.size(); i++)
          {
            if (listBrand.get(i).name.toLowerCase().contains(str.toLowerCase())) {
              listSearch.add(listBrand.get(i));
            }
          }
          if(listSearch.size()==0)
        	  txtNoResult.setVisibility(8);
          Collections.sort(listSearch, Brand.nameBrandComparator);
        
          filterDetailAdapter = new FilterBrandAdapter2(context, R.layout.category_and_brand_grid_item, listSearch);
          lstViewBrand.setAdapter(filterDetailAdapter);
          prepareData(listSearch);
 //        onWindowFocusChanged(true, ChooseBrandFragment.this.rootView);
          ChooseBrandFragment.this.imageViewCut.setVisibility(0);
          ChooseBrandFragment.this.txtNoResult.setVisibility(0);
        }
      });
      
      
      this.imageViewCut.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ChooseBrandFragment.this.editSearchbrand.setText("");
          ChooseBrandFragment.this.imageViewCut.setVisibility(8);
        }
      });
      
      

 //   }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.indexList = new ArrayList<Object[]>();
    this.listBrand = new ArrayList<Brand>();
    this.listSearch = new ArrayList<Brand>();
    this.categoryDB = DBManager.getCategoryDBAdapter(this.context);
    this.brandDB = DBManager.getBrandDBAdapter(this.context);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.rootView = paramLayoutInflater.inflate(R.layout.fragment_choose_brand, paramViewGroup, false);
    init(this.rootView);
    return this.rootView;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mGestureDetector.onTouchEvent(paramMotionEvent))
    {
      Log.e("mGestureDetector ", "mGestureDetector =" + this.mGestureDetector.toString());
      return true;
    }
    return false;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean, View paramView)
  {
    this.sideIndexHeight = this.sideIndex.getHeight();
    this.sideIndex.removeAllViews();
    this.indexList = createIndex(this.lststr);
    this.indexListSize = this.indexList.size();
    int i = (int)Math.floor(this.sideIndexHeight / 20);
    int j = this.indexListSize;
    double d1;
    if (j <= i) {
      d1 = this.indexListSize / j;
      
      for (double d2 = 1.0D;; d2 += d1)
      {
        if (d2 > this.indexListSize)
        {
          this.sideIndex.setOnTouchListener(new View.OnTouchListener()
          {
            public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
            {
              ChooseBrandFragment.sideIndexX = paramAnonymousMotionEvent.getX();
              ChooseBrandFragment.sideIndexY = paramAnonymousMotionEvent.getY();
              ChooseBrandFragment.this.displayListItem();
              return false;
            }
          });
//          return;
//          j /= 2;
//          break;
        }
        String str = ((Object[])this.indexList.get(-1 + (int)d2))[0].toString();
        TextView localTextView = new TextView(getActivity());
        localTextView.setText(str);
        localTextView.setGravity(17);
        localTextView.setTextSize(18.0F);
        localTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0F));
        this.sideIndex.addView(localTextView);
      }
    }
//TODO:

  }
  
  public void onclickCutButton(View paramView)
  {
    this.editSearchbrand.setText("");
    this.imageViewCut.setVisibility(8);
  }
  
  void prepareData(ArrayList<Brand> paramArrayList)
  {
    this.lststr = new ArrayList<String>();
    Iterator<Brand> localIterator = paramArrayList.iterator();
    
      while (localIterator.hasNext()) {
    	  String str = ((Brand)localIterator.next()).name;
          this.lststr.add(str);
      }
     
  }
  
  class SideIndexGestureListener
    extends GestureDetector.SimpleOnGestureListener
  {
    SideIndexGestureListener() {}
    
    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      ChooseBrandFragment.sideIndexX -= paramFloat1;
      ChooseBrandFragment.sideIndexY -= paramFloat2;
      if ((ChooseBrandFragment.sideIndexX >= 0.0F) && (ChooseBrandFragment.sideIndexY >= 0.0F)) {
        ChooseBrandFragment.this.displayListItem();
      }
      return super.onScroll(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    }
  }
}

