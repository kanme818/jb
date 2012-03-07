package ky.jokebox;

import ky.jokebox.common.GPSUtil;
import ky.jokebox.widgets.GifView;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class JokeBoxActivity.
 */
public class JokeBoxActivity extends Activity {
	private final Context context = JokeBoxActivity.this;

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

		Button getGPS = (Button) findViewById(R.id.getGPS);
		getGPS.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Location location = GPSUtil.getCurrentLocation(context);
				if (location == null) {
					Toast.makeText(context, "无法获取定位信息", 5000).show();
					return;
				}
				Toast.makeText(
						context,
						"GPS获取坐标:" + location.getLatitude() + ","
								+ location.getLongitude(), 5000).show();
			}
		});
	}
}