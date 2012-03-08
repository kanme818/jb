package ky.jokebox.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class VerticalTextView extends TextView {

	public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public VerticalTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public VerticalTextView(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {
		canvas.translate(getHeight(), 0);
		canvas.rotate(90);
		super.onDraw(canvas);
	}

	protected void onMeasury(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		setMeasuredDimension(200, 200);
	}

}
