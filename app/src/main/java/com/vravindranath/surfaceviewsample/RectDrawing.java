package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class RectDrawing extends DrawableObject {

	private Path mPath;
	private Paint mPaint;
	
	public RectDrawing(Path path, Paint paint) {
		mPath = path;
		mPaint = paint;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawPath(mPath, mPaint);
	}

}
