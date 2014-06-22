package com.hitechno.sogoods.views;

import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.hitechno.sogoods.R;
import com.hitechno.sogoods.constant.ConstantsHelper;

public class FuturaEditText extends EditText {

	/*
	 * Permissible values ​​for the "typeface" attribute.
	 */
	private final static int FUTURA_STD_BOLD = 0;
	private final static int FUTURA_STD_BOLD_OBLIQUE = 1;
	private final static int FUTURA_STD_BOOK = 2;
	private final static int FUTURA_STD_BOOK_OBLIQUE = 3;
	private final static int FUTURA_STD_HEAVY = 4;
	private final static int FUTURA_STD_HEAVY_OBLIQUE = 5;
	private final static int FUTURA_STD_LIGHT = 6;
	private final static int FUTURA_STD_MEDIUM = 7;
	private final static int FUTURA_STD_MEDIUM_OBLIQUE = 8;

	public FuturaEditText(Context context) {
		super(context);
	}

	public FuturaEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setTypeface(context, attrs);
	}

	public FuturaEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setTypeface(context, attrs);
	}

	private void setTypeface(Context ctx, AttributeSet attrs) {
		TypedArray array = ctx.obtainStyledAttributes(attrs,
				R.styleable.FuturaTextView);
		int typeface = array.getInt(R.styleable.FuturaTextView_typeface, 0);
		setTypeface(ctx, typeface);
		array.recycle();
	}

	public boolean setTypeface(Context ctx, int typefaceValue) {
		Typeface typeface = null;
		try {
			typeface = getFont(ctx, typefaceValue);
		} catch (Exception e) {
			Log.e(ConstantsHelper.LOG_TAG, getClass().getSimpleName() + ": "
					+ e.getMessage());
			return false;
		}

		setTypeface(typeface);
		return true;
	}

	private static final Hashtable<Integer, SoftReference<Typeface>> fontCache = new Hashtable<Integer, SoftReference<Typeface>>();

	public static Typeface getFont(Context c, int typefaceValue) {
		synchronized (fontCache) {
			if (fontCache.get(typefaceValue) != null) {
				SoftReference<Typeface> reference = fontCache
						.get(typefaceValue);
				if (reference.get() != null) {
					return reference.get();
				}
			}

			Typeface typeface = getTypeface(c, typefaceValue);
			fontCache.put(typefaceValue, new SoftReference<Typeface>(typeface));

			return typeface;
		}
	}

	private static Typeface getTypeface(Context context, int typefaceValue) {
		Typeface typeface;
		switch (typefaceValue) {
		case FUTURA_STD_BOLD:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Bold.ttf");
			break;
		case FUTURA_STD_BOLD_OBLIQUE:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Bold.ttf");
			break;
		case FUTURA_STD_BOOK:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/FuturaStd-Book.otf");
			break;
		case FUTURA_STD_BOOK_OBLIQUE:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/FuturaStd-BookOblique.otf");
			break;
		case FUTURA_STD_HEAVY:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Bold.ttf");
			break;
		case FUTURA_STD_HEAVY_OBLIQUE:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Bold.ttf");
			break;
		case FUTURA_STD_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Light.ttf");
			break;
		case FUTURA_STD_MEDIUM:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Medium.ttf");
			break;
		case FUTURA_STD_MEDIUM_OBLIQUE:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Medium.ttf");
			break;
		default:
			Log.e(ConstantsHelper.LOG_TAG, FuturaEditText.class.getSimpleName()
					+ ": " + "Missing typeface. Default to FuturaStd-Medium");
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/HelveticaNeue-Medium.ttf");
		}
		return typeface;
	}
}
