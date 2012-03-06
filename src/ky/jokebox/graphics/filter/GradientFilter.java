/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics.filter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class GradientFilter.
 */
public class GradientFilter extends BaseFilter {

	/** The TAG. */
	private String TAG = "GradientFilter";

	/** X轴偏移量。和渐变方向有关，总是从渐变的起始方向计算。. */
	public int xOffset;

	/** Y轴偏移量。和渐变方向有关，总是从渐变的起始方向计算。. */
	public int yOffset;

	/** 渐变方向，默认Y轴正方向（Android坐标系）. */
	public int direction;

	/** 起始色. */
	public int startColor;

	/** 终止色. */
	public int endColor;

	/** X轴负方向. */
	public static final int MINUS_X = -1;

	/** X轴正方向. */
	public static final int POSITIVE_X = 1;

	/** Y轴负方向. */
	public static final int MINUS_Y = -2;

	/** Y轴正方向. */
	public static final int POSITIVE_Y = 2;

	/**
	 * 使用默认设置.
	 */
	public GradientFilter() {
		super();
	}

	/**
	 * 控制渐变方向.
	 * 
	 * @param direction
	 *            the direction
	 */
	public GradientFilter(int direction) {
		super();
		this.direction = direction;
	}

	/**
	 * Instantiates a new gradient filter.
	 * 
	 * @param xOffset
	 *            the x offset
	 * @param yOffset
	 *            the y offset
	 * @param direction
	 *            the direction
	 */
	public GradientFilter(int direction, int xOffset, int yOffset) {
		super();
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.direction = direction;
	}

	/**
	 * Instantiates a new gradient filter.
	 * 
	 * @param startColor
	 *            the start color
	 * @param endColor
	 *            the end color
	 */
	public GradientFilter(int startColor, int endColor) {
		super();
		this.startColor = startColor;
		this.endColor = endColor;
	}

	/**
	 * Instantiates a new gradient filter.
	 * 
	 * @param xOffset
	 *            the x offset
	 * @param yOffset
	 *            the y offset
	 * @param direction
	 *            the direction
	 * @param startColor
	 *            the start color
	 * @param endColor
	 *            the end color
	 */
	public GradientFilter(int direction, int xOffset, int yOffset,
			int startColor, int endColor) {
		super();
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.direction = direction;
		this.startColor = startColor;
		this.endColor = endColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kanyong.jokebox.graphics.Filter#doFilter(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap doFilter(Bitmap bmp) {
		int xBegin = 0, yBegin = 0, xEnd = 0, yEnd = 0;
		int height = bmp.getHeight();
		int width = bmp.getWidth();
		switch (direction) {
		case MINUS_X:
			xBegin = width - Math.min(xOffset, width);
			break;
		case POSITIVE_X:
			xBegin = xBegin + Math.min(xOffset, width);
			xEnd = width;
			break;
		case MINUS_Y:
			yBegin = height - Math.min(yOffset, height);
			break;
		case POSITIVE_Y:
			yBegin = yBegin + Math.min(yOffset, height);
			yEnd = height;
			break;

		default:
			return bmp;
		}

		Log.d(TAG, "xBegin: " + xBegin + " yBegin: " + yBegin + " xEnd: "
				+ xEnd + " yEnd: " + yEnd);
		Log.d(TAG, "height: " + height + " width: " + width);

		Paint paint = new Paint();
		paint.setAntiAlias(false);
		LinearGradient shader = new LinearGradient(xBegin, yBegin, xEnd, yEnd,
				startColor, endColor, TileMode.MIRROR);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(
				android.graphics.PorterDuff.Mode.DST_IN));

		Bitmap resultBmp = Bitmap.createBitmap(bmp);
		Canvas canvas = new Canvas(resultBmp);
		canvas.drawRect(0, 0, width, height, paint);
		return resultBmp;
	}

	@Override
	public void reset() {
		/** X轴偏移量。和渐变方向有关，总是从渐变的起始方向计算。. */
		this.xOffset = 0;
		/** Y轴偏移量。和渐变方向有关，总是从渐变的起始方向计算。. */
		this.yOffset = 0;
		/** 渐变方向，默认Y轴正方向（Android坐标系）. */
		this.direction = POSITIVE_Y;
		/** 起始色. */
		this.startColor = 0xFFFFFFFF;
		/** 终止色. */
		this.endColor = 0x00FFFFFF;

	}
}
