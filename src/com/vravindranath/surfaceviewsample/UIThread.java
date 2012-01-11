package com.vravindranath.surfaceviewsample;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class UIThread extends Thread {

    private static boolean toRun = false;
    private CanvasView canvasView;
    private SurfaceHolder surfaceHolder;
	
	public UIThread(CanvasView canvasView) {
		this.canvasView = canvasView;
		surfaceHolder = canvasView.getHolder();
	}
	
	public boolean isThreadRunning() {
		return toRun;
	}

	public void setRunning(boolean run) {
		toRun = run;
	}
	
	@Override
	public void run() {
		Canvas c;
	    while (toRun) {
	            c = null;
	            try {
	                c = surfaceHolder.lockCanvas(null);
	                canvasView.onDraw(c);
	            } finally {
	                if (c != null) {
	                    surfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
	    }
	}

}
