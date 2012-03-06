/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.widgets;

import ky.jokebox.graphics.PS;
import ky.jokebox.graphics.filter.Reflection3DFilter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class Gallery3DAdapter.
 */
public class Gallery3DAdapter extends BaseAdapter {

	/** The TAG. */
	private static String TAG = "JokeBox: Gallery3DAdapter";

	/** The context. */
	private Context context;

	/** The image ids. */
	private Object[] imageIds;

	private View[] cache;

	/** The is loop. */
	private boolean isLoop;

	/** The default width. */
	private int defaultWidth;

	/** The default height. */
	private int defaultHeight;

	/**
	 * Instantiates a new gallery3 d adapter.
	 * 
	 * @param context
	 *            the context
	 * @param imageIds
	 *            the image ids
	 */
	public Gallery3DAdapter(Context context, Integer[] imageIds) {
		super();
		this.context = context;
		this.imageIds = imageIds;
	}

	/**
	 * Instantiates a new gallery3 d adapter.
	 * 
	 * @param context
	 *            the context
	 * @param imageIds
	 *            the image ids
	 * @param isLoop
	 *            the is loop
	 * @param defaultWidth
	 *            the default width
	 * @param defaultHeight
	 *            the default height
	 */
	public Gallery3DAdapter(Context context, Object[] imageIds, boolean isLoop,
			int defaultWidth, int defaultHeight) {
		super();
		this.context = context;
		this.imageIds = imageIds;
		this.isLoop = isLoop;
		this.defaultWidth = defaultWidth;
		this.defaultHeight = defaultHeight;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return isLoop ? Integer.MAX_VALUE : imageIds.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return reCalculatePosition(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return reCalculatePosition(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d(TAG, "position: " + position);

		return cache[reCalculatePosition(position)];
	}

	public void prepare() {
		cache = new View[imageIds.length];
		for (int i = 0; i < imageIds.length; i++) {
			cache[i] = createView(i);
		}
	}

	protected View createView(int position) {
		Bitmap srcBmp = getResourceBitmap(reCalculatePosition(position));

		Bitmap resultBmp = PS.apply(srcBmp, new Reflection3DFilter()).getBmp();

		int viewHeight = 0, viewWidth = 0;
		if (defaultHeight == 0 && defaultWidth == 0) {
			viewHeight = resultBmp.getHeight();
			viewWidth = resultBmp.getWidth();
		} else {
			viewHeight = defaultHeight;
			viewWidth = defaultWidth;
		}

		Log.d(TAG, "view height: " + viewHeight);
		Log.d(TAG, "view width: " + viewWidth);

		ImageView imageView = new ImageView(context);
		imageView.setImageBitmap(resultBmp);
		// 设置imageView大小 ，也就是最终显示的图片大小
		imageView.setLayoutParams(new Gallery3D.LayoutParams(viewWidth,
				viewHeight));
		return imageView;
	}

	/**
	 * Gets the resource bitmap.
	 * 
	 * @param position
	 *            the position
	 * @return the resource bitmap
	 */
	public Bitmap getResourceBitmap(int position) {
		Bitmap originalImage = BitmapFactory.decodeResource(
				context.getResources(), (Integer) imageIds[position]);
		return originalImage;
	}

	/**
	 * Gets the scale.
	 * 
	 * @param focused
	 *            the focused
	 * @param offset
	 *            the offset
	 * @return the scale
	 */
	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

	/**
	 * Re calculate position.
	 * 
	 * @param position
	 *            the position
	 * @return the int
	 */
	private int reCalculatePosition(int position) {
		if (position < 0) {
			position = position + imageIds.length;
		}
		return position % imageIds.length;
	}

}
