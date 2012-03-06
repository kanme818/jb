/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics;

import android.graphics.Bitmap;

// TODO: Auto-generated Javadoc
/**
 * 所有滤镜接口.
 */
public interface Filter {

	/**
	 * 应用滤镜.
	 * 
	 * @param bmp
	 *            Bitmap 对象
	 * @return Bitmap 对象
	 */
	public Bitmap doFilter(Bitmap bmp);

	/**
	 * 重置滤镜参数。建议在此方法中设置默认参数，以保证重置后的参数和默认的一致。.
	 */
	public void reset();
}
