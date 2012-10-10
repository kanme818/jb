package ky.jokebox.activity;

import java.util.ArrayList;
import java.util.List;

import ky.jokebox.R;
import ky.jokebox.pulltorefresh.OnRefreshListener;
import ky.jokebox.pulltorefresh.PullToRefreshListView;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 下拉刷新ListView.
 */
public class PullToRefreshActivity extends Activity {
	private List<String> data;
	private BaseAdapter adapter;
	private PullToRefreshListView listView;
	private RefreshDataAsynTask mRefreshAsynTask;
	private int index;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pull2refresh_main);
		init();
		listView = (PullToRefreshListView) findViewById(R.id.listView);
		adapter = new BaseAdapter() {
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView tv = new TextView(getApplicationContext());
				tv.setText(data.get(position));
				return tv;
			}

			public long getItemId(int position) {
				return 0;
			}

			public Object getItem(int position) {
				return null;
			}

			public int getCount() {
				return data.size();
			}
		};
		listView.setAdapter(adapter);

		listView.setonRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				mRefreshAsynTask = new RefreshDataAsynTask();
				mRefreshAsynTask.execute(null, null, null);
			}
		});

	}

	private void init() {
		data = new ArrayList<String>();
		for (int i = 0; i < 30; i++) {
			data.add("item " + i);
		}
	}

	private class RefreshDataAsynTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			index++;
			data.add("genius" + index);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			adapter.notifyDataSetChanged();
			listView.onRefreshComplete();
		}

	}
}
