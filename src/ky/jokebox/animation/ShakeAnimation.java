/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.animation;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class ShakeAnimation.
 */
public class ShakeAnimation {

	/** 内部通信ID. */
	private static final int MSG_ID = 0x0123;

	/** 激烈. */
	public static final int ANIM_ABOIL = 0x0000;

	/** 正常. */
	public static final int ANIM_NORMAL = 0x0001;

	/** 平缓. */
	public static final int ANIM_MILD = 0x0002;

	/** 需要播放动画的控件. */
	private final View view;

	/** 主要逻辑在这个里面. */
	private final Handler handler;

	/** 控件的布局文件. */
	private final LinearLayout.LayoutParams params;

	/** 动画参数. */
	private final Controls ctrl;

	/**
	 * Instantiates a new shake animation.
	 * 
	 * @param view
	 *            the view
	 */
	public ShakeAnimation(View view) {
		super();
		this.view = view;
		this.params = (LinearLayout.LayoutParams) view.getLayoutParams();
		this.ctrl = new Controls();
		this.handler = new ShakeHandler();
	}

	/**
	 * 使用默认参数播放动画.
	 */
	public void shake() {
		ctrl.reset();
		ctrl.getTimer().schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(MSG_ID);
			}
		}, 0, 30);
	}

	/**
	 * Shake.
	 * 
	 * @param TYPE
	 *            the tYPE
	 */
	public void shake(int TYPE) {
		switch (TYPE) {
		case ANIM_ABOIL:
			ctrl.setPos(ctrl.pos_1);
			break;
		case ANIM_NORMAL:

			break;
		case ANIM_MILD:
			ctrl.setPos(ctrl.pos_3);
			break;
		default:
			break;
		}
		shake();
	}

	/**
	 * The Class ShakeHandler.
	 */
	private class ShakeHandler extends Handler {

		/** The ori left margin. */
		final int oriLeftMargin = params.leftMargin;

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MSG_ID) {
				/** The pos. */
				int[] pos = ctrl.getPos();

				if (ctrl.getIndex() < pos.length) {
					params.leftMargin = oriLeftMargin + pos[ctrl.getIndex()];
					view.setLayoutParams(params);
					ctrl.indexPlus();
				} else {
					ctrl.reset();
				}
			}
		}

	}

	/**
	 * The Class Controls.
	 */
	private class Controls {

		/** The index. */
		private int index = 0;

		/** The timer. */
		private Timer timer;

		/** The pos. */
		private int[] pos;

		private final int[] pos_1 = new int[] { -32, 24, -16, 8, 0 };
		private final int[] pos_2 = new int[] { -8, 6, -4, 2, 0 };
		private final int[] pos_3 = new int[] { -4, 3, -2, 1, 0 };

		/**
		 * Instantiates a new controls.
		 */
		public Controls() {
			super();
			timer = new Timer();
			pos = pos_2;
		}

		/**
		 * Gets the index.
		 * 
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}

		/**
		 * Gets the timer.
		 * 
		 * @return the timer
		 */
		public Timer getTimer() {
			return timer;
		}

		/**
		 * Reset.
		 */
		public void reset() {
			index = 0;
			timer.cancel();
			timer = new Timer();
		}

		/**
		 * Index plus.
		 */
		public void indexPlus() {
			index++;
		}

		/**
		 * Gets the pos.
		 * 
		 * @return the pos
		 */
		public int[] getPos() {
			return pos;
		}

		/**
		 * Sets the pos.
		 * 
		 * @param pos
		 *            the new pos
		 */
		public void setPos(int[] pos) {
			this.pos = pos;
		}

	}
}
