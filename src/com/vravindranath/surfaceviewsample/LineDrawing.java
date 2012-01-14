package com.vravindranath.surfaceviewsample;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class LineDrawing extends DrawableObject {

	private static final float CONTROL_POINT_RADIUS = 8;
	private ArrayList<PointF> controlPoints;
	private Path mPath;
	private Paint mPaint;
	
	private boolean isSelected = true;
	
	public void setSelected(boolean isSelected) {
		isSelected = true;
	}

	public LineDrawing(Path path, Paint paint) {
		mPath = path;
		mPaint = paint;
		
		controlPoints = new ArrayList<PointF>();
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawPath(mPath, mPaint);
		if (isSelected) {
			drawSelection(canvas);
		}
	}
	
	public boolean isStartPointEqualTo(float x, float y) {
		return controlPoints.get(controlPoints.size() - 1).equals(x, y);
	}
	
	public void removeLastPoint() {
		synchronized (controlPoints) {
			controlPoints.remove(controlPoints.size() - 1);
		}
	}
	
	@Override
	public void addControlPoint(float x, float y) {
		super.addControlPoint(x, y);
		synchronized (controlPoints) {
			controlPoints.add(new PointF(x, y));
		}
		mPath.lineTo(x, y);
	}
	
	@Override
	public void drawSelection(Canvas canvas) {
		synchronized (controlPoints) {
			for (PointF controlPoint : controlPoints) {
				canvas.drawCircle(controlPoint.x, controlPoint.y,
						CONTROL_POINT_RADIUS, mPaint);
			}
		}
	}
}
