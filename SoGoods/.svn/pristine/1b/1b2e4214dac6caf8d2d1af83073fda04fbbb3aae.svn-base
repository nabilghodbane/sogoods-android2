package com.hitechno.sogoods.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class QuestionTabView extends ImageView {

	private static final int HEIGHT = 64;
	private Drawable drawable;
	
	public QuestionTabView(Context context) {
		super(context);
		
		setMinimumHeight(HEIGHT);
	}

	public QuestionTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setMinimumHeight(HEIGHT);
	}

	public QuestionTabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setMinimumHeight(HEIGHT);
	}
	
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
	
	public void setImageDrawable(Drawable drawable) {
		this.drawable = drawable;
		super.setImageDrawable(drawable);
	}
	
	protected void onDraw(Canvas canvas) {
		drawable.draw(canvas);
	}
}
