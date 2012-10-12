package ky.jokebox.waterfall;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class WaterFallScrollView extends ScrollView {
	private OnScrollListener onScrollListener;

	public WaterFallScrollView(Context context) {
		super(context);
	}

	public WaterFallScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WaterFallScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public OnScrollListener getOnScrollListener() {
		return onScrollListener;
	}

	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (onScrollListener != null) {
			onScrollListener.onScroll(this);
		}
	}

	// 自定义的监听接口，满足条件是调用其中的方法，执行相应的操作
	public static interface OnScrollListener {
		/**
		 * called when the view scrolled to the bottom edge.
		 * 
		 * @param v
		 *            ControlableScrollView
		 */
		public void onScroll(WaterFallScrollView v);
	}

}
