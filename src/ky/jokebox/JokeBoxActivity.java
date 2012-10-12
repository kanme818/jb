package ky.jokebox;

import ky.jokebox.activity.GifViewActivity;
import ky.jokebox.activity.PullToRefreshActivity;
import ky.jokebox.activity.WaterFallActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

// TODO: Auto-generated Javadoc
/**
 * The Class JokeBoxActivity.
 */
public class JokeBoxActivity extends Activity {
	private final Context context = JokeBoxActivity.this;

	private Spinner demoList;
	private Button showDemo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		demoList = (Spinner) findViewById(R.id.spinner1);
		showDemo = (Button) findViewById(R.id.button1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, context.getResources()
						.getStringArray(R.array.demo_list));

		demoList.setAdapter(adapter);
		showDemo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Class<?> clazz = null;
				switch (demoList.getSelectedItemPosition()) {
				case 0:
					clazz = GifViewActivity.class;
					break;

				case 1:
					clazz = PullToRefreshActivity.class;
					break;
					
				case 2:
					clazz = WaterFallActivity.class;
					break;

				default:
					break;
				}
				if (clazz != null) {
					startActivity(new Intent(context, clazz));
				}
			}
		});
	}

}