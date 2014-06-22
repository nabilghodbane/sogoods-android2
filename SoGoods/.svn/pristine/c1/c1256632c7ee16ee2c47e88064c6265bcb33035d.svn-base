package com.hitechno.sogoods.drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * Paints a valid sign using <code>Canvas</code>.
 *
 */
public class ValidDrawable extends Drawable {

	private ColorFilter colorFilter;
	private int alpha;

	public ValidDrawable() {
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		drawTickMark(canvas);
		canvas.restore();
	}

	private void drawTickMark(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 189, 163, 156);
		paint.setAntiAlias(true);
		paint.setStrokeJoin(Paint.Join.BEVEL);
		paint.setStrokeWidth(3);

		float[] points = { 31, 38, 44, 49, 44, 49, 65, 18 };
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
