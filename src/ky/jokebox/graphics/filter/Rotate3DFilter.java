/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics.filter;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;

// TODO: Auto-generated Javadoc
/**
 * 3D旋转滤镜.
 */
public class Rotate3DFilter extends BaseFilter {

	/** 转动角度. */
	public float angle;

	/** 转动轴，默认绕图像顶边旋转. */
	public int spiale;

	/** X轴自身的偏移量，取值0-1。例如：0.5代表X轴向Y轴正方向移动50%的图像高度。仅在转动轴为TOP/BOTTOM时有效。. */
	public float xAxisOffset;

	/** Y轴自身的偏移量，取值0-1。例如：0.5代表Y轴向X轴正方向移动50%的图像宽度。仅在转动轴为LEFT/RIGHT时有效。. */
	public float yAxisOffset;

	/** 转动轴，绕图像左边旋转. */
	public static final int LEFT = 1;

	/** 转动轴，绕图像顶边旋转. */
	public static final int TOP = 2;

	/** 转动轴，绕图像右边旋转. */
	public static final int RIGHT = 3;

	/** 转动轴，绕图像底边旋转. */
	public static final int BOTTOM = 4;

	/**
	 * Instantiates a new rotate3 d filter.
	 */
	public Rotate3DFilter() {
		super();
	}

	/**
	 * Instantiates a new rotate3 d filter.
	 * 
	 * @param angle
	 *            转动角度
	 * @param spiale
	 *            转动轴
	 * @param xAxisOffset
	 *            X轴自身的偏移量，取值0-1。例如：0.5代表X轴向Y轴正方向移动50%的图像高度。仅在转动轴为TOP/BOTTOM时有效。
	 * @param yAxisOffset
	 *            Y轴自身的偏移量，取值0-1。例如：0.5代表Y轴向X轴正方向移动50%的图像宽度。仅在转动轴为LEFT/RIGHT时有效。
	 */
	public Rotate3DFilter(float angle, int spiale, float xAxisOffset,
			float yAxisOffset) {
		super();
		this.angle = angle;
		this.spiale = spiale;
		this.xAxisOffset = xAxisOffset;
		this.yAxisOffset = yAxisOffset;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kanyong.jokebox.image.filter.Filter#doFilter(kanyong.jokebox.image.filter
	 * .Filterable)
	 */
	@Override
	public Bitmap doFilter(Bitmap bmp) {
		Camera camera = new Camera();
		Matrix matrix = new Matrix();
		int height = bmp.getHeight();
		int width = bmp.getWidth();

		int newOriginX = 0, newOriginY = 0;
		switch (spiale) {
		case LEFT:
			newOriginY = height / 2;
			newOriginX = (int) (newOriginX + yAxisOffset * width);
			camera.rotateY(angle);
			break;
		case TOP:
			newOriginX = width / 2;
			newOriginY = (int) (newOriginY + xAxisOffset * height);
			camera.rotateX(angle);
			break;
		case RIGHT:
			newOriginY = height / 2;
			newOriginX = (int) (width - yAxisOffset * width);
			camera.rotateY(angle);
			break;
		case BOTTOM:
			newOriginX = width / 2;
			newOriginY = (int) (height - xAxisOffset * height);
			camera.rotateX(angle);
			break;

		default:
			break;
		}

		camera.getMatrix(matrix);

		matrix.preTranslate(-newOriginX, -newOriginY);
		matrix.postTranslate(newOriginX, newOriginY);

		Bitmap resultBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
				bmp.getHeight(), matrix, true);

		return resultBmp;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kanyong.jokebox.graphics.Filter#reset()
	 */
	@Override
	public void reset() {
		/** 转动角度. */
		this.angle = 45f;

		/** 转动轴，默认绕图像顶边旋转. */
		this.spiale = TOP;

		/** X轴自身的偏移量，取值0-1。例如：0.5代表X轴向Y轴正方向移动50%的图像高度。仅在转动轴为TOP/BOTTOM时有效。. */
		this.xAxisOffset = 0.0f;

		/** Y轴自身的偏移量，取值0-1。例如：0.5代表Y轴向X轴正方向移动50%的图像宽度。仅在转动轴为LEFT/RIGHT时有效。. */
		this.yAxisOffset = 0.0f;

	}

}
