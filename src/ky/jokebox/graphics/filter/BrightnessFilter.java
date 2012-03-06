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
 * 亮度滤镜.
 */
public class BrightnessFilter extends BaseFilter {

	/** 亮度绝对值. */
	private float absBrightness;

	/**
	 * Instantiates a new brightness filter.
	 * 
	 * @param absBrightness
	 *            the abs brightness
	 */
	public BrightnessFilter(float absBrightness) {
		super();
		this.absBrightness = absBrightness;
	}

	/**
	 * Gets the abs brightness.
	 * 
	 * @return the abs brightness
	 */
	public float getAbsBrightness() {
		return absBrightness;
	}

	/**
	 * Sets the abs brightness.
	 * 
	 * @param absBrightness
	 *            the new abs brightness
	 */
	public void setAbsBrightness(float absBrightness) {
		this.absBrightness = absBrightness;
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

		matrix.set(new float[] { 1, 0, 0, 0, absBrightness, 0, 1, 0, 0,
				absBrightness, 0, 0, 1, 0, absBrightness, 0, 0, 0, 1, 0 });

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
		this.absBrightness = 0.0f;
	}

}
