package ky.jokebox.waterfall;

import ky.jokebox.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WaterFallDetailActivity extends Activity {
	private final Activity self  = WaterFallDetailActivity.this;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.water_fall_detail);

		btn = (Button) findViewById(R.id.btnBack);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				self.finish();

			}
		});
	}
}
