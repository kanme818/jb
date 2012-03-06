/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics;

import android.graphics.Bitmap;

// TODO: Auto-generated Javadoc
/**
 * The Class PS.
 */
public class PS {

	/**
	 * Apply.
	 * 
	 * @param bmp
	 *            the bmp
	 * @param filter
	 *            the filter
	 * @return the filterable
	 */
	public static Filterable apply(Bitmap bmp, Filter filter) {
		Bitmap newBmp = filter.doFilter(bmp);
		return new FilterableBitmap(newBmp);
	}

	/**
	 * Quick apply.
	 * 
	 * @param bmp
	 *            the bmp
	 * @param filter
	 *            the filter
	 * @return the bitmap
	 */
	public static Bitmap quickApply(Bitmap bmp, Filter filter) {
		return filter.doFilter(bmp);
	}

}

final class FilterableBitmap implements Filterable {
	private Bitmap bmp;

	public FilterableBitmap(Bitmap bmp) {
		super();
		this.bmp = bmp;
	}

	public Filterable apply(Filter filter) {
		Bitmap bmp = filter.doFilter(this.getBmp());
		this.setBmp(bmp);
		return this;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

}
