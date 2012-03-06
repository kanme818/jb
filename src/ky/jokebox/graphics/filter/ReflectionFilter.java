/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics.filter;

import ky.jokebox.graphics.PS;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;

// TODO: Auto-generated Javadoc
/**
 * 倒影滤镜.
 */
public class ReflectionFilter extends BaseFilter {

	/** 原图和倒影的间隙. */
	public int reflectionGap;

	/** 倒影和原图的比率，倒影高度 = 原图高度 * rate. */
	public float reflectRate;

	/** 渐变前景色. */
	public int startColor;

	/** 渐变背景色. */
	public int endColor;

	/** 倒影矩阵. */
	protected Matrix matrix;

	/**
	 * Instantiates a new reflection filter.
	 */
	public ReflectionFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * kanyong.jokebox.image.filter.Filter#doFilter(android.graphics.Bitmap)
	 */
	@Override
	public Bitmap doFilter(Bitmap bmp) {
		// 倒影（已处理完整效果）
		Bitmap reflectionBmp = createReflectionBmp(bmp);
		// 原图高度、宽度
		int srcHeight = bmp.getHeight();
		int srcWidth = bmp.getWidth();
		// 倒影高度、宽度
		int reflectionHeight = reflectionBmp.getHeight();
		int reflectionWidth = reflectionBmp.getWidth();
		// 最终结果的高度、宽度
		int resultHeight = srcHeight + reflectionGap + reflectionHeight;
		int resultWidth = Math.max(srcWidth, reflectionWidth);

		// 创建空的图片
		Bitmap resultBmp = Bitmap.createBitmap(resultWidth, resultHeight,
				Config.ARGB_8888);
		// 绘制原图
		Canvas canvas = new Canvas(resultBmp);
		// 如果倒影经过旋转处理，宽度可能大于原图宽度，所以原图需要向右偏其底边才能对齐倒影的顶边。
		canvas.drawBitmap(bmp, (reflectionWidth - srcWidth) / 2, 0, null);
		canvas.drawBitmap(reflectionBmp, 0, srcHeight + reflectionGap, null);
		return resultBmp;
	}

	/**
	 * 创建倒影，并附加效果.
	 * 
	 * @param bmp
	 *            the bmp
	 * @return the bitmap
	 */
	protected Bitmap createReflectionBmp(Bitmap bmp) {
		Bitmap resultBmp = applyGradientFilter(applyRotateFilter(createRawBitmap(bmp)));
		return resultBmp;
	}

	/**
	 * 从原图中创建倒影.
	 * 
	 * @param bmp
	 *            the bmp
	 * @return the bitmap
	 */
	protected Bitmap createRawBitmap(Bitmap bmp) {
		int srcWidth = bmp.getWidth();
		int srcHeight = bmp.getHeight();
		// 倒影的初始高度
		int reflectHeight = (int) (srcHeight * reflectRate);

		Bitmap reflectBmp = Bitmap.createBitmap(bmp, 0,
				(srcHeight - reflectHeight), srcWidth, reflectHeight, matrix,
				false);
		return reflectBmp;
	}

	/**
	 * 应用旋转效果.
	 * 
	 * @param bmp
	 *            the bmp
	 * @return the bitmap
	 */
	protected Bitmap applyRotateFilter(Bitmap bmp) {
		// 平面倒影不旋转
		return bmp;
	}

	/**
	 * 应用渐变滤镜.
	 * 
	 * @param bmp
	 *            the bmp
	 * @return the bitmap
	 */
	protected Bitmap applyGradientFilter(Bitmap bmp) {
		Bitmap gradientedBmp = PS.apply(bmp,
				new GradientFilter(GradientFilter.POSITIVE_Y)).getBmp();
		return gradientedBmp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see kanyong.jokebox.graphics.Filter#reset()
	 */
	@Override
	public void reset() {
		/** 原图和倒影的间隙. */
		this.reflectionGap = 4;

		/** 倒影和原图的比率，倒影高度 = 原图高度 * rate. */
		this.reflectRate = 0.5f;

		/** 渐变前景色. */
		this.startColor = 0xFFFFFFFF;

		/** 渐变背景色. */
		this.endColor = 0x00FFFFFF;

		/** 倒影矩阵. */
		this.matrix = new Matrix();
		matrix.preScale(1, -1);

	}

}
