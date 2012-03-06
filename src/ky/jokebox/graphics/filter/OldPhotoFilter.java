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

public class OldPhotoFilter extends BaseFilter {

	@Override
	public Bitmap doFilter(Bitmap bmp) {
		Bitmap resultBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(resultBmp);
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		ColorMatrix matrix = new ColorMatrix();

		matrix.set(new float[] { 0.393f, 0.769f, 0.189f, 0, 0, 0.349f, 0.686f,
				0.168f, 0, 0, 0.272f, 0.534f, 0.131f, 0, 0, 0, 0, 0, 1, 0 });

		paint.setColorFilter(new ColorMatrixColorFilter(matrix));
		canvas.drawBitmap(bmp, 0, 0, paint);
		return resultBmp;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
