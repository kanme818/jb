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
 * The Class ContrastFilter.
 */
public class ContrastFilter extends BaseFilter {

	/** The rate. */
	private float rate;

	/**
	 * Instantiates a new contrast filter.
	 * 
	 * @param rate
	 *            the rate
	 */
	public ContrastFilter(float rate) {
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

		matrix.set(new float[] { rate, 0, 0, 0, 0, 0, rate, 0, 0, 0, 0, 0,
				rate, 0, 0, 0, 0, 0, 1, 0 });

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
