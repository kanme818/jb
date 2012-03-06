/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics.filter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

// TODO: Auto-generated Javadoc
/**
 * 饱和度滤镜.
 */
public class SaturationFilter extends BaseFilter {

	/** 饱和度比例，默认1. */
	private float rate;

	/**
	 * Instantiates a new saturation filter.
	 * 
	 * @param rate
	 *            the rate
	 */
	public SaturationFilter(float rate) {
		super();
		this.rate = rate;
	}

	/**
	 * Gets the rate.
	 * 
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * Sets the rate.
	 * 
	 * @param rate
	 *            the new rate
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kanyong.jokebox.graphics.Filter#doFilter(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap doFilter(Bitmap bmp) {
		Bitmap resultBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(resultBmp);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		ColorMatrix matrix = new ColorMatrix();
		// 饱和度
		matrix.setSaturation(rate);

		paint.setColorFilter(new ColorMatrixColorFilter(matrix));
		canvas.drawBitmap(bmp, 0, 0, paint);
		return resultBmp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kanyong.jokebox.graphics.Filter#reset()
	 */
	@Override
	public void reset() {
		this.rate = 1;
	}

}
