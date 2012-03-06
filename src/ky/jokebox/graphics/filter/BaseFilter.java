/*
 * Joke Box 
 * 
 * @auth Yong.Kan 2012
 */
package ky.jokebox.graphics.filter;

import ky.jokebox.graphics.Filter;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseFilter.
 */
public abstract class BaseFilter implements Filter {

	/**
	 * Instantiates a new base filter.
	 */
	public BaseFilter() {
		super();
		// 在基类构造器中调用重置参数方法，保证滤镜创建和重置后的参数值相同。
		reset();
	}

}
