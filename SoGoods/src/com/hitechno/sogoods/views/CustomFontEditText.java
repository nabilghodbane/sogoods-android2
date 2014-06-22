package com.hitechno.sogoods.views;

import com.hitechno.sogoods.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class CustomFontEditText extends EditText {
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
		m_CustomFont = values.getString(R.styleable.CustomFontEditText_customFont);
		if (m_CustomFont != null) {
			Log.d("CustomFontTextView", "Got font \"" + m_CustomFont + "\" from attributes.");
		} else {
			Log.d("CustomFontTextView", "No font defined in the xml");
		}
		values.recycle();
		changeToCustomFont();
	}

	public CustomFontEditText(Context context) {
		super(context);
	}

	public CustomFontEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		changeFontFromAttributes(attrs);
	}

	public CustomFontEditText(Context context, AttributeSet attrs, int defStyle) {
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
