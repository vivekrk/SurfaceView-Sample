package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;

public abstract class DrawableObject {
	
	abstract public void draw(Canvas canvas);

	public void addControlPoint(float x, float y) {
		
	}
	
	abstract public void drawSelection(Canvas canvas);
	
	public boolean isStartPointEqualTo(float x, float y) {
		return false;
	}
	
	public void removeLastPoint() {
		
	}
	
}
