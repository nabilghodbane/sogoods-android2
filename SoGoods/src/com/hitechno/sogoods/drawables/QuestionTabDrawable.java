package com.hitechno.sogoods.drawables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Paints an empty tab using <code>Canvas</code>.
 *
 */
public class QuestionTabDrawable extends Drawable {

	private int alpha;
	private ColorFilter colorFilter;
	
	public QuestionTabDrawable() {
		
	}

	@Override
	public void draw(Canvas canvas) {
		int startX = canvas.getClipBounds().left;
		int endX = canvas.getClipBounds().left;
		
		float startLeftX = (float) (canvas.getClipBounds().right * 0.16);
		float startLeftY = canvas.getClipBounds().bottom;
		float startTopLeftX = (float) (canvas.getClipBounds().right * 0.19);
		float startTopLeftY = (float) (canvas.getClipBounds().bottom * 0.4);
		
		float endTopRightX = (float) (canvas.getClipBounds().right * 0.81);
		float endTopRightY = (float) (canvas.getClipBounds().bottom * 0.4);
		float endRightX = (float) (canvas.getClipBounds().right * 0.84);
		float endRightY = canvas.getClipBounds().bottom;
		
		Path path = new Path();
		path.moveTo(startLeftX, startLeftY);
		path.lineTo(startTopLeftX, startTopLeftY);
		path.lineTo(endTopRightX, endTopRightY);
		path.lineTo(endRightX, endRightY);
		path.lineTo(startLeftX, startLeftY);
		path.close();
		
		int[] colors = { Color.parseColor("#635451"), Color.parseColor("#382d28") };
		LinearGradient shader = new LinearGradient(startX, startTopLeftY, endX, endRightY, colors, null, Shader.TileMode.REPEAT);
		
		Paint paint = new Paint();
		paint.setShader(shader);
		paint.setAntiAlias(true);
		
		canvas.drawPath(path, paint);
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
	public void setColorFilter(ColorFilter colorFilter) {
		this.colorFilter = colorFilter;
	}
	
	/**
    *
    * @param xStart vector start point
    * @param yStart
    * @param xEnd vector end point
    * @param yEnd
    * @param ovalRectOUT RectF to store result
    * @param enum direction left/right
    * @return start angle
    */
//   public static float getSemicircle(float xStart, float yStart, float xEnd,
//           float yEnd, RectF ovalRectOUT, SIDE direction) {
//
//       float centerX = xStart + ((xEnd - xStart) / 2);
//       float centerY = yStart + ((yEnd - yStart) / 2);
//
//       double xLen = (xEnd - xStart);
//       double yLen = (yEnd - yStart);
//       float radius = (float) (Math.sqrt(xLen * xLen + yLen * yLen) / 2);
//
//       RectF oval = new RectF((float) (centerX - radius),
//               (float) (centerY - radius), (float) (centerX + radius),
//               (float) (centerY + radius));
//
//       ovalRectOUT.set(oval);
//
//       double radStartAngle = 0;
//       if (direction == SIDE.LEFT) {
//           radStartAngle = Math.atan2(yStart - centerY, xStart - centerX);
//       } else {
//           radStartAngle = Math.atan2(yEnd - centerY, xEnd - centerX);
//       }
//       float startAngle = (float) Math.toDegrees(radStartAngle);
//
//       return startAngle;
//
//   }
}
