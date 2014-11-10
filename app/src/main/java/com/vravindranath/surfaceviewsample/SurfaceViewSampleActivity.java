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
	private CanvasView view;
	private static final int CLEAR = 100;
	
	private FrameLayout surfaceViewFrame;

	private RadioGroup mode;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mode = (RadioGroup) findViewById(R.id.drawingMode);
		mode.setOnCheckedChangeListener(this);

		surfaceViewFrame = (FrameLayout) findViewById(R.id.surfaceviewFrame);
		view = new CanvasView(this);
		surfaceViewFrame.addView(view, 0);

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
			view.clearCanvas();
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
			view.setDrawingMode(Constants.FREE_DRAWING);
			break;

		case R.id.line_drawing:
			view.setDrawingMode(Constants.LINE_DRAWING);
			Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
			break;

		case R.id.rectangle:
			view.setDrawingMode(Constants.RECT_DRAWING);
			Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.select:
			view.setDrawingMode(Constants.SELECT);
			Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}
}