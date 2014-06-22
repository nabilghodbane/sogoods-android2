package com.hitechno.sogoods.drawables;

import com.hitechno.sogoods.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

/**
 * Paints a map marker using <code>Canvas</code>.
 *
 */
public class MapMarkerDrawable extends Drawable {

	private ColorFilter colorFilter;
	private int alpha;
	
	private Context context;
	private String label;
	
	public MapMarkerDrawable(Context context, String text) {
		this.context = context;
		this.label = text;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		drawMarker(canvas);
		canvas.restore();
		
		canvas.save();
		drawLabel(canvas);
		canvas.restore();
	}
	
	private void drawMarker(Canvas canvas) {
		int topY = canvas.getClipBounds().top;
		int bottomY = canvas.getClipBounds().bottom;
		int centerX = canvas.getClipBounds().centerX();
		
		float startTopLeftX = (float) (canvas.getClipBounds().left);
		float startTopLeftY = (float) (canvas.getClipBounds().bottom * 0.3);
		float endTopRightX = (float) (canvas.getClipBounds().right);
		float endTopRightY = (float) (canvas.getClipBounds().bottom * 0.3);
		
		Path path = new Path();
		path.moveTo(centerX, bottomY);
		path.lineTo(startTopLeftX, startTopLeftY);
		path.addArc(new RectF(topY, startTopLeftX, endTopRightY, endTopRightX), -180, 180);
		path.lineTo(centerX, bottomY);
		path.close();
		
		int[] colors = { Color.parseColor("#635451"), Color.parseColor("#382d28") };
		LinearGradient shader = new LinearGradient(startTopLeftX, startTopLeftY, endTopRightX, endTopRightY, colors, null, Shader.TileMode.REPEAT);
		
		Paint paint = new Paint();
		paint.setShader(shader);
		paint.setAntiAlias(true);
		
		canvas.drawPath(path, paint);
	}
	
	private void drawLabel(Canvas canvas) {
		int centerX = canvas.getClipBounds().centerX();
		
		Paint textPaint = new Paint();
		textPaint.setColor(context.getResources().getColor(R.color.sogoods_neutral_color));
		textPaint.setStyle(Style.FILL);
		textPaint.setTextSize(16);
		textPaint.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/FuturaStd-Medium.otf"));
		textPaint.setAntiAlias(true);
		
		float textWidth = textPaint.measureText(label);
		float offsetX = centerX - textWidth/2;
		
		canvas.drawText(label, offsetX, (float) (canvas.getClipBounds().bottom * 0.2), textPaint);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		colorFilter = cf;
	}
	
	public Bitmap toBitmap() {
		this.setBounds(0, 0, 50, 150);
		
		int width = getBounds().width();
	    int height = getBounds().height();
	    
	    Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas canvas = new Canvas(bitmap); 
	    this.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
	    this.draw(canvas);

	    return bitmap;
	}
}
