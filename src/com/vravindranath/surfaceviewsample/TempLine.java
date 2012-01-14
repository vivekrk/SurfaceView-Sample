package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class TempLine {
	private float startX;
	private float startY;

	private float stopX;
	private float stopY;
	
	private Paint mPaint;
	private boolean shouldDrawline = false;

	public void setStartPoint(float x, float y) {
		startX = x;
		startY = y;
		Log.d("TEST", "setStartPoint: (" + startX + "," + startY + ")");
	}

	public void setEndPoint(float x, float y) {
		stopX = x;
		stopY = y;
		
		Log.d("TEST", "setEndPoint: (" + x + "," + y + ")");
	}

	public TempLine(Paint paint) {
		mPaint = paint;
	}

	public void draw(Canvas canvas) {
		if (shouldDrawline) {
			canvas.drawLine(startX, startY, stopX, stopY, mPaint);
			Log.d("TEST", "draw: (startX, startY): (" + startX + "," + startY + ")" + 
					"(stopX, stopY): (" + stopX + "," + stopY + ")");
		}
	}
	
	public boolean setShouldDrawLine(boolean shouldDrawline) {
		this.shouldDrawline = shouldDrawline;
		return shouldDrawline;
	}

	public void resetLine() {
		startX = -1;
		startY = -1;
		stopX = -1;
		stopY = -1;
		mPaint = null;
		
		Log.d("TEST", "resetLine: (startX, startY): (" + startX + "," + startY + ")" + 
		"(stopX, stopY): (" + stopX + "," + stopY + ")");
	}
}