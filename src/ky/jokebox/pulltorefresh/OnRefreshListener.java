package ky.jokebox.pulltorefresh;

public interface OnRefreshListener {

	int NORMAL = 0;
	int PULL_TO_REFRESH = 1;
	int RELEASE_TO_REFRESH = 2;
	int LOADING = 3;

	int RATIO = 3;

	public void onRefresh();
}
