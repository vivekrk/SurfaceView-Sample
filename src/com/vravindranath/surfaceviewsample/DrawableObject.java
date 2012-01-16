package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;

public abstract class DrawableObject {
	
	abstract public void draw(Canvas canvas);

	public void addControlPoint(float x, float y) {
		
	}
	
	public void setSelected(boolean isSelected) {
		
	}
	
	abstract public void drawSelection(Canvas canvas);
	
	public void removeLastPoint() {
		
	}

	public boolean isPointInObject(float x, float y) {
		return false;
	}
}
