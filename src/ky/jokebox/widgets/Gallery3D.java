/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.widgets;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

public class Gallery3D extends Gallery {
	private static String TAG = "JokeBox: Gallery3D";
	private Camera camera = new Camera();
	// 最大转动角度
	public int maxRotationAngle = 60;
	// 最大缩放值
	public int maxZoom = -300;
	// 半径值
	public int coveflowCenter;

	/**
	 * Instantiates a new gallery flow.
	 * 
	 * @param context
	 *            the context
	 */
	public Gallery3D(Context context) {
		super(context);
		// 支持替换 ,执行getChildStaticTransformation方法
		this.setStaticTransformationsEnabled(true);
	}

	/**
	 * Instantiates a new gallery flow.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 */
	public Gallery3D(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setStaticTransformationsEnabled(true);
	}

	/**
	 * Instantiates a new gallery flow.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 * @param defStyle
	 *            the def style
	 */
	public Gallery3D(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setStaticTransformationsEnabled(true);
	}

	/**
	 * 获取当前控件的中心点在屏幕上的坐标（X轴）.
	 * 
	 * @param view
	 *            the view
	 * @return the center of view
	 */
	protected static int getCenterOfView(View view) {
		Log.d(TAG, "view left :" + view.getLeft());
		Log.d(TAG, "view width :" + view.getWidth());
		return view.getLeft() + view.getWidth() / 2;
	}

	/**
	 * 内容区域的中心点在控件中的距离.
	 * 
	 * @return the center of coverflow
	 */
	protected int getCenterOfCoverflow() {
		return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
				+ getPaddingLeft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		coveflowCenter = getCenterOfCoverflow();
		super.onSizeChanged(w, h, oldw, oldh);
	}

	// 控制gallery中每个图片的旋转(重写的gallery中方法)
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.Gallery#getChildStaticTransformation(android.view.View,
	 * android.view.animation.Transformation)
	 */
	protected boolean getChildStaticTransformation(View child, Transformation t) {
		// 取得当前子view的中心点坐标
		final int childCenter = getCenterOfView(child);
		Log.d(TAG, "childCenter：" + childCenter);
		// view的宽度
		final int childWidth = child.getWidth();
		// 旋转角度
		int rotationAngle = 0;
		// 重置转换状态
		t.clear();
		// 设置转换类型
		t.setTransformationType(Transformation.TYPE_MATRIX);
		// 如果图片位于中心位置不需要进行旋转
		if (childCenter == coveflowCenter) {
			transformImageBitmap((ImageView) child, t, 0);
		} else {
			// 根据图片在gallery中的位置来计算图片的旋转角度
			rotationAngle = (int) (((float) (coveflowCenter - childCenter) / childWidth) * maxRotationAngle);
			Log.d(TAG, "rotationAngle:" + rotationAngle);
			// 如果旋转角度绝对值大于最大旋转角度返回（-mMaxRotationAngle或mMaxRotationAngle;）
			if (Math.abs(rotationAngle) > maxRotationAngle) {
				rotationAngle = (rotationAngle < 0) ? -maxRotationAngle
						: maxRotationAngle;
			}
			transformImageBitmap((ImageView) child, t, rotationAngle);
		}
		return true;
	}

	/**
	 * Transform image bitmap.
	 * 
	 * @param child
	 *            the child
	 * @param t
	 *            the t
	 * @param rotationAngle
	 *            the rotation angle
	 */
	protected void transformImageBitmap(ImageView child, Transformation t,
			int rotationAngle) {
		// 对效果进行保存
		camera.save();
		final Matrix imageMatrix = t.getMatrix();
		// 图片高度
		final int imageHeight = child.getLayoutParams().height;
		// 图片宽度
		final int imageWidth = child.getLayoutParams().width;
		// 返回旋转角度的绝对值
		final int rotation = Math.abs(rotationAngle);

		// Z轴正向移动，缩小图片。
		camera.translate(0.0f, 0.0f, 100.0f); // 参
		// 转动角度越大，Z轴值越大，图越小
		if (rotation < maxRotationAngle) {
			float zoomAmount = (float) (maxZoom + (rotation * 1.5));
			camera.translate(0.0f, 0.0f, zoomAmount);
		}

		// 以中心，绕Y轴旋转固定角度
		camera.rotateY(rotationAngle);
		camera.getMatrix(imageMatrix);
		imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
		imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
		camera.restore();
	}
}
