package ky.jokebox.activity;

import java.util.ArrayList;

import ky.jokebox.R;
import ky.jokebox.waterfall.WaterFallItem;
import ky.jokebox.waterfall.WaterFallScrollView;
import ky.jokebox.waterfall.WaterFallScrollView.OnScrollListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class WaterFallActivity extends Activity {
	private final Activity self = WaterFallActivity.this;

	private LayoutInflater inflater;

	// UI components
	private LinearLayout contentLayout;
	private WaterFallScrollView scrollView;

	// store the layouts of all columns, one column one element
	private ArrayList<LinearLayout> columns = new ArrayList<LinearLayout>();

	// parameters
	private int itemWidth;
	private int itemCount;
	private static final int AMOUNT_PER_LOADING = 6;
	private static final int COLUMN_COUNT = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.water_fall_main);

		itemCount = 0;

		itemWidth = this.getWindowManager().getDefaultDisplay().getWidth()
				/ COLUMN_COUNT;
		contentLayout = (LinearLayout) findViewById(R.id.content_layout);
		scrollView = (WaterFallScrollView) findViewById(R.id.scrollView1);
		scrollView.setOnScrollListener(scrollListener);
		inflater = LayoutInflater.from(self);

		// add 3 columns
		for (int i = 0; i < COLUMN_COUNT; i++) {
			// column does not need to control the padding by itself
			LinearLayout.LayoutParams itemParam = new LinearLayout.LayoutParams(
					itemWidth, LayoutParams.WRAP_CONTENT);
			LinearLayout column = new LinearLayout(this);
			column.setOrientation(LinearLayout.VERTICAL);
			column.setLayoutParams(itemParam);

			// store 3 columns to a container for filling the items
			columns.add(column);
			contentLayout.addView(column);
		}

		// add 10 items
		for (int i = 0; i < 10; i++) {
			int currentIndex = itemCount + i;
			LinearLayout item = createItemLayout(currentIndex, null);
			addItem(currentIndex, item);
		}

	}

	private LinearLayout createItemLayout(int index, WaterFallItem itemInfo) {
		LinearLayout item = (LinearLayout) inflater.inflate(
				R.layout.water_fall_item, null);
		item.setPadding(5, 5, 5, 5);
		return item;
	}

	private void addItem(int index, LinearLayout item) {
		int columnIndex = index % COLUMN_COUNT;
		LinearLayout column = columns.get(columnIndex);
		column.addView(item);
		itemCount++;
	}

	private OnScrollListener scrollListener = new OnScrollListener() {

		@Override
		public void onScroll(WaterFallScrollView v) {
			Log.d("", "on scrolling");
		}
	};

}
