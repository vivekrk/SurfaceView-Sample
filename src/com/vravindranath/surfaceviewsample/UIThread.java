package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class UIThread extends Thread {

    private static boolean mToRun = false;
    private CanvasView mCanvasView;
    private SurfaceHolder mSurfaceHolder;
	
	public UIThread(CanvasView canvasView) {
		this.mCanvasView = canvasView;
		mSurfaceHolder = canvasView.getHolder();
	}
	
	public boolean isThreadRunning() {
		return mToRun;
	}

	public void setRunning(boolean run) {
		mToRun = run;
	}
	
	@Override
	public void run() {
		Canvas c;
	    while (mToRun) {
	            c = null;
	            try {
	                c = mSurfaceHolder.lockCanvas(null);
	                mCanvasView.onDraw(c);
	            } finally {
	                if (c != null) {
	                    mSurfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
	    }
	}

}
