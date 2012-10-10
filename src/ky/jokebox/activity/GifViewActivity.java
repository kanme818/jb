package ky.jokebox.activity;

import ky.jokebox.R;
import ky.jokebox.widgets.GifView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Gif 图片显示.
 */
public class GifViewActivity extends Activity {
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gif_view);

		LinearLayout ll = (LinearLayout) findViewById(R.id.linear1);
		GifView gv1 = new GifView(this, R.drawable.g1);
		ll.addView(gv1);
	}
}
