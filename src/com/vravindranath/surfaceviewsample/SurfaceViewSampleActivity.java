package com.vravindranath.surfaceviewsample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class SurfaceViewSampleActivity extends Activity implements OnCheckedChangeListener {
	/** Called when the activity is first created. */
	private CanvasView mView;
	private static final int CLEAR = 100;
	
	private FrameLayout mSurfaceViewFrame;

	private RadioGroup mMode;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mMode = (RadioGroup) findViewById(R.id.drawingMode);
		mMode.setOnCheckedChangeListener(this);

		mSurfaceViewFrame = (FrameLayout) findViewById(R.id.surfaceviewFrame);
		mView = new CanvasView(this);
		mSurfaceViewFrame.addView(mView, 0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, CLEAR, 0, "Clear");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case CLEAR:
			mView.clearCanvas();
			break;
		}
		return true;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Toast.makeText(getApplicationContext(), "onConfigurationChanged", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.free_drawing:
			mView.setDrawingMode(Constants.FREE_DRAWING);
			break;

		case R.id.line_drawing:
			mView.setDrawingMode(Constants.LINE_DRAWING);
			break;

		case R.id.rectangle:
			mView.setDrawingMode(Constants.RECT_DRAWING);
			Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.select:
			mView.setDrawingMode(Constants.SELECT);
			break;

		default:
			break;
		}
	}
}