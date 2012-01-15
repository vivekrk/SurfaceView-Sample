package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.graphics.Paint;

public class TempLine {
	private float mStartX;
	private float mStartY;

	private float mStopX;
	private float mStopY;
	
	private Paint mPaint;
	private boolean mShouldDrawline = false;

	public void setStartPoint(float x, float y) {
		mStartX = x;
		mStartY = y;
//		Log.d("TEST", "setStartPoint: (" + startX + "," + startY + ")");
	}

	public void setEndPoint(float x, float y) {
		mStopX = x;
		mStopY = y;
		
//		Log.d("TEST", "setEndPoint: (" + x + "," + y + ")");
	}

	public TempLine(Paint paint) {
		mPaint = paint;
	}

	public void draw(Canvas canvas) {
		if (mShouldDrawline) {
			canvas.drawLine(mStartX, mStartY, mStopX, mStopY, mPaint);
//			Log.d("TEST", "draw: (startX, startY): (" + startX + "," + startY + ")" + 
//					"(stopX, stopY): (" + stopX + "," + stopY + ")");
		}
	}
	
	public boolean setShouldDrawLine(boolean shouldDrawline) {
		this.mShouldDrawline = shouldDrawline;
		return shouldDrawline;
	}

	public void resetLine() {
		mStartX = -1;
		mStartY = -1;
		mStopX = -1;
		mStopY = -1;
		mPaint = null;
		
//		Log.d("TEST", "resetLine: (startX, startY): (" + startX + "," + startY + ")" + 
//		"(stopX, stopY): (" + stopX + "," + stopY + ")");
	}
}