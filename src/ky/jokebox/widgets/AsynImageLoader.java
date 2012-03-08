/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.widgets;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class AsynImageLoader.
 */
public class AsynImageLoader {

	/** The cache. */
	private static Map<String, Bitmap> cache = new HashMap<String, Bitmap>();

	/** The pool. */
	public static ExecutorService pool = Executors.newFixedThreadPool(3);

	/**
	 * 异步读取。每次都从指定url读取图片，并且不缓存结果.
	 * 
	 * @param view
	 *            the view
	 * @param url
	 *            the url
	 */
	public static void loadImage(ImageView view, String url) {
		loadImage(view, url, false, false);
	}

	/**
	 * Load image.
	 * 
	 * @param view
	 *            the view
	 * @param url
	 *            the url
	 * @param cacheIt
	 *            the cache it
	 * @param fromCache
	 *            the from cache
	 */
	public static void loadImage(final ImageView view, final String url,
			boolean cacheIt, boolean fromCache) {
		loadImage(url, cacheIt, fromCache, new ImageCallback() {
			@Override
			public void imageLoaded(Bitmap imageDrawable) {
				view.setImageBitmap(imageDrawable);
			}
		});
	}

	/**
	 * Clear image map.
	 */
	public static void clearImageMap() {
		cache.clear();
	}

	/**
	 * Load drawable.
	 * 
	 * @param url
	 *            the url
	 * @param callback
	 *            the callback
	 * @return the bitmap
	 */
	public static void loadImage(final String url, final boolean cacheIt,
			boolean fromCache, final ImageCallback callback) {
		Bitmap bmp = null;

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				callback.imageLoaded((Bitmap) msg.obj);
			}
		};

		if (fromCache) {
			bmp = cache.get(url);
		}

		if (bmp != null) {
			handler.sendMessage(handler.obtainMessage(0, bmp));
		} else {
			Runnable task = new Runnable() {
				public void run() {
					Bitmap drawable = loadImageFromUrl(url);
					if (cacheIt) {
						cache.put(url, drawable);
					}
					handler.sendMessage(handler.obtainMessage(0, drawable));
				};
			};
			pool.execute(task);
		}

	}

	/**
	 * Load image from url.
	 * 
	 * @param imageUrl
	 *            the image url
	 * @return the bitmap
	 */
	protected static Bitmap loadImageFromUrl(String imageUrl) {
		try {
			URL url = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10 * 1000);
			conn.connect();
			InputStream inputStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
			inputStream.close();
			conn.disconnect();
			return bitmap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * The Interface ImageCallback.
	 */
	public interface ImageCallback {

		/**
		 * Image loaded.
		 * 
		 * @param imageDrawable
		 *            the image drawable
		 */
		public void imageLoaded(Bitmap imageDrawable);
	}
}
