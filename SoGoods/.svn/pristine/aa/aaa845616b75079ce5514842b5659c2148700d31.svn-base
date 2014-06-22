package com.hitechno.sogoods.adapters;

import java.util.HashMap;
import java.util.List;

import com.hitechno.sogoods.R;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class SearchMenuLeftAdapter extends BaseExpandableListAdapter{
	Context context;
	List<String> lstDataGroup;
	private HashMap<String, List<String>> lstDataChild;
	
	public SearchMenuLeftAdapter(Context context, List<String> lstDataGroup,
			HashMap<String, List<String>> lstDataChild) {
		super();
		this.context = context;
		this.lstDataGroup = lstDataGroup;
		this.lstDataChild = lstDataChild;
	}

	@Override
	public Object getChild(int groupPos, int childPos) {
		return this.lstDataChild.get(this.lstDataGroup.get(groupPos)).get(childPos);
	}
	
	public void swapLstDataChild(HashMap<String, List<String>> ls){
		this.lstDataChild = ls;
		notifyDataSetChanged();
	}

	@Override
	public long getChildId(int groupPos, int childPos) {
		return childPos;
	}

	@Override
		public View getChildView(final int groupPos,final int childPos, boolean isLastChild, View convertView,
				ViewGroup parent) {
		final String childText = (String)getChild(groupPos, childPos);
		String temp[];
		temp = childText.split("--");
		Log.e("temp","temp="+temp[0].toString()+"---"+temp[1].toString()+"---");
		 
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.search_menuleft_listitem, null);
		}
		TextView txtLstChild = (TextView)convertView.findViewById(R.id.lbListitem);
		
		String htmlString=temp[1]+"<br/>"+"<font color='#B18F7C'>"+temp[2]+"</font>";
				
		if(temp[2].equals("novalue")){
			txtLstChild.setText(temp[1]);
		}else{
			txtLstChild.setText(Html.fromHtml(htmlString), TextView.BufferType.SPANNABLE);
		}
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPos) {
		Log.e("lst data child ","lst data child=" + this.lstDataChild.get(this.lstDataGroup.get(groupPos)).size());
		return this.lstDataChild.get(this.lstDataGroup.get(groupPos)).size();
	}

	@Override
	public Object getGroup(int groupPos) {
		return this.lstDataGroup.get(groupPos);
	}

	@Override
	public int getGroupCount() {
		return this.lstDataGroup.size();
	}

	@Override
	public long getGroupId(int groupPos) {
		return groupPos;
	}

	@Override
	public View getGroupView(int groupPos, boolean isExpand, View convertView, ViewGroup parent) {
		String groupTitle = (String)getGroup(groupPos);
		if(convertView==null){
			LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.search_menuleft_listgroup, null);
		}
		TextView lbListGroup = (TextView)convertView.findViewById(R.id.lblstGroup);
		lbListGroup.setTypeface(null,Typeface.BOLD);
		lbListGroup.setText(groupTitle);
		
		TextView lbCount = (TextView)convertView.findViewById(R.id.lbCount);
		int count = getChildrenCount(groupPos);
		lbCount.setText(""+count);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPos, int childPos) {
		return true;
	}
		

}
