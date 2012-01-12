package com.vravindranath.surfaceviewsample;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class LineDrawing extends DrawableObject {

	private ArrayList<PointF> controlPoints;
	private Path mPath;
	private Paint mPaint;

	public LineDrawing(Path path, Paint paint) {
		mPath = path;
		mPaint = paint;
		
		controlPoints = new ArrayList<PointF>();
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawPath(mPath, mPaint);
	}
	
	@Override
	public void addControlPoint(float x, float y) {
		super.addControlPoint(x, y);
		controlPoints.add(new PointF(x, y));
	}
}
