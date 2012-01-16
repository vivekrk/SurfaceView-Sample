package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.Log;

public class FreeDrawing extends DrawableObject {

	private Path mPath;
	private Paint mPaint;
	
	private boolean mIsSelected = false;
	
	private Rect mBoundingRect;
	
	private Paint mSelectionPaint;
	
	public FreeDrawing(Path path, Paint paint) {
		mPath = path;
		mPaint = paint;
		
		mBoundingRect = new Rect();
		setSelectionPaint();
	}
	
	public void setSelected(boolean isSelected) {
		mIsSelected = isSelected;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if (mIsSelected) {
			drawSelection(canvas);
		}
		canvas.drawPath(mPath, mPaint);
	}
	
	private void calcBoundingRect() {
		RectF rect = new RectF();
		mPath.computeBounds(rect, true);
		rect.roundOut(mBoundingRect);
	}
	
	private void setSelectionPaint() {
		mSelectionPaint = new Paint();
		mSelectionPaint.setAntiAlias(true);
		mSelectionPaint.setDither(true);
		mSelectionPaint.setColor(Color.BLUE);
		mSelectionPaint.setStyle(Paint.Style.STROKE);
		mSelectionPaint.setStrokeJoin(Paint.Join.ROUND);
		mSelectionPaint.setStrokeCap(Paint.Cap.ROUND);
		mSelectionPaint.setStrokeWidth(mPaint.getStrokeWidth() + 8);
	}
	
	@Override
	public void drawSelection(Canvas canvas) {
		Log.d("TEST", "Drawing selection");
		canvas.drawPath(mPath, mSelectionPaint);
	}

	public boolean isPointInObject(float x, float y) {
		Region region = new Region();
		
		Region clip = new Region();
		calcBoundingRect();
		clip.set(mBoundingRect);
		region.setPath(mPath, clip);
		if(region.contains((int) x, (int) y)) {
			return true;
		}
		
		return false;
	}
}
