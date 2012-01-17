package com.vravindranath.surfaceviewsample;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

	private static final float STROKE_WIDTH = 20;

	private UIThread mUIThread;

	private Path mPath;
	private Paint mPaint;

	private ArrayList<DrawableObject> mOldObjectedToDraw;
	
	private DrawableObject mDrawableObject = null;

	private float mX;
	private float mY;
	private float TOUCH_TOLERANCE = 8;
	
	private TempLine mTempline;

	private int mMode = Constants.FREE_DRAWING;
	
	private ArrayList<DrawableObject> mObjectsToDraw;

	public CanvasView(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		mObjectsToDraw = new ArrayList<DrawableObject>();
		
		mPath = new Path();
	}

	public void clearCanvas() {
		mPath.reset();
		synchronized (mObjectsToDraw) {
			mObjectsToDraw.clear();
		}
		mDrawableObject = null;
	}
	
	public void setDrawingMode(int drawingMode) {
		mMode = drawingMode;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		setPaintProperties();

		if (canvas != null) {
			canvas.drawColor(Color.WHITE);
			synchronized (mObjectsToDraw) {
				for (DrawableObject drawableObject : mObjectsToDraw) {
					drawableObject.draw(canvas);
				}
			}
			
			if (mTempline != null) {
				mTempline.draw(canvas);
			}
		}
	}

	public void stopUIThread() {
		mUIThread.setRunning(false);
	}

	private void setPaintProperties() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(STROKE_WIDTH);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		mOldObjectedToDraw = mObjectsToDraw;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			switch (mMode) {
			case Constants.SELECT:
				mDrawableObject = null;
				mDrawableObject = getObjectAtPoint(x, y);
				
				if(mDrawableObject == null) {
					deselectAllObjects();
				} else {
					mDrawableObject.setSelected(true);
				}
				break;
				
			case Constants.FREE_DRAWING:
				movePathTo(x, y);
				if(mDrawableObject == null) {
					mDrawableObject = new FreeDrawing(mPath, mPaint);
				}
				
				break;
				
			case Constants.LINE_DRAWING:
				if(mDrawableObject == null) {
					movePathTo(x, y);
					mDrawableObject = new LineDrawing(mPath, mPaint);
					mTempline = new TempLine(mPaint);
					mDrawableObject.addControlPoint(x, y);
					mTempline.setStartPoint(x, y);
				}
				break;
				
			case Constants.RECT_DRAWING:
				movePathTo(x, y);
				if(mDrawableObject == null) {
					mDrawableObject = new RectDrawing(mPath, mPaint);
				}
				break;
			}
			addObjectToDraw(mDrawableObject);
			break;

		case MotionEvent.ACTION_MOVE:
			switch (mMode) {
			case Constants.FREE_DRAWING:
				float dx = Math.abs(x - mX);
				float dy = Math.abs(y - mY);

				if (dx >= TOUCH_TOLERANCE  || dy >= TOUCH_TOLERANCE) {
					mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);

					mX = x;
					mY = y;
				}
				break;
				
			case Constants.LINE_DRAWING:
				mTempline.setEndPoint(x, y);
				mTempline.setShouldDrawLine(true);
				break;
			}
			break;

		case MotionEvent.ACTION_UP:
			switch (mMode) {
			case Constants.FREE_DRAWING:
				mPath.moveTo(x, y);
				mDrawableObject = null;
				break;
				
			case Constants.LINE_DRAWING:
				mDrawableObject.addControlPoint(x, y);
				
				mTempline.setShouldDrawLine(false);
//				templine.resetLine();
//				drawableObject = null;
				mTempline.setStartPoint(x, y);
				break;
			}
			break;
		}
		return true;
	}

	private void deselectAllObjects() {
		for (DrawableObject drawbleObject : mObjectsToDraw) {
			drawbleObject.setSelected(false);
		}
	}

	private void addObjectToDraw(DrawableObject drawableObject) {
		if (drawableObject != null) {
			synchronized (mObjectsToDraw) {
				mObjectsToDraw.add(drawableObject);
			}
		}
	}
	
	private void movePathTo(float x, float y) {
		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	private DrawableObject getObjectAtPoint(float x, float y) {
		for (DrawableObject drawableObject : mObjectsToDraw) {
			if (drawableObject.isPointInObject(x, y)) {
				return drawableObject;
			}
		}
		return null;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mUIThread = new UIThread(this);
		mUIThread.setRunning(true);
		mUIThread.start();
	}

	public void restoreOldPath() {
		mObjectsToDraw = mOldObjectedToDraw;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mUIThread.setRunning(false);
	}

}
