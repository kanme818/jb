package ky.jokebox;

import ky.jokebox.widgets.GifView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class JokeBoxActivity.
 */
public class JokeBoxActivity extends Activity {

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linear1);
		GifView gv1 = new GifView(this, R.drawable.g1);
		ll.addView(gv1);
	}
}