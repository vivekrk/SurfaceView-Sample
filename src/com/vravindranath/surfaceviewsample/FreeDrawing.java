package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class FreeDrawing extends DrawableObject {

	private Path mPath;
	private Paint mPaint;
	
	private boolean mIsSelected = false;
	
	public FreeDrawing(Path path, Paint paint) {
		mPath = path;
		mPaint = paint;
	}
	
	public void setSelected(boolean isSelected) {
		isSelected = true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawPath(mPath, mPaint);
		if (mIsSelected) {
			drawSelection(canvas);
		}
	}

	@Override
	public void drawSelection(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
}
