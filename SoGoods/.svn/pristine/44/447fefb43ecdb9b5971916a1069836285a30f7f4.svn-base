package com.hitechno.sogoods.views;

import com.hitechno.sogoods.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class CustomFontTextView extends TextView {
	private String m_CustomFont = null;
	
	private void changeToCustomFont() {
		if (isInEditMode()) return;
		if (m_CustomFont == null) return;
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), m_CustomFont);
		if (tf != null) {
			setTypeface(tf);
		}
	}
	
	private void changeFontFromAttributes(AttributeSet attrs) {
		int[] attrsArray = {R.attr.customFont};
		TypedArray values = getContext().obtainStyledAttributes(attrs, attrsArray);
		m_CustomFont = values.getString(R.styleable.CustomFontTextView_customFont);
		if (m_CustomFont != null) {
			Log.d("CustomFontTextView", "Got font \"" + m_CustomFont + "\" from attributes.");
		} else {
			Log.d("CustomFontTextView", "No font defined in the xml");
		}
		values.recycle();
		changeToCustomFont();
	}
	
	public CustomFontTextView(Context context) {
		super(context);
	}

	public CustomFontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		changeFontFromAttributes(attrs);
	}

	public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		changeFontFromAttributes(attrs);
	}
	
	public String getCustomFont() {
		return m_CustomFont;
	}
	
	public void setCustomFont(String customFont) {
		m_CustomFont = customFont;
		changeToCustomFont();
		postInvalidate();
	}
}
