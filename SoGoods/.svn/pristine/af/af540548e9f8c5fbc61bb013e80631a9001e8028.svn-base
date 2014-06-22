package com.hitechno.sogoods.drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Paints an invalid sign using <code>Canvas</code>
 *
 */
public class InvalidDrawable extends Drawable {

	private ColorFilter colorFilter;
	private int alpha;
	
	public InvalidDrawable() { }

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
        drawCrossMark(canvas);
        canvas.restore();
	}
	
	private void drawCrossMark(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 189, 163, 156);
		paint.setAntiAlias(true);
		paint.setStrokeJoin(Paint.Join.BEVEL);
		paint.setStrokeWidth(2);
		
		float[] points = { 31, 16, 63, 48, 31, 48, 63, 16 };
		canvas.drawLines(points, paint);
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
}
