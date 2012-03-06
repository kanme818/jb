/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics;

import android.graphics.Bitmap;

public interface Filterable {
	public Filterable apply(Filter filter);

	public Bitmap getBmp();

	public void setBmp(Bitmap bmp);

}
