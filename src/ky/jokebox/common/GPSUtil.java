package ky.jokebox.common;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * GPS相关帮助类.
 */
public class GPSUtil {

	/** The Constant TAG. */
	private static final String TAG = "JokeBox: GPSHelper";

	/**
	 * 获取当前坐标，优先使用GPS定位，如果没有则使用网路定位。如果均无法获得位置信息则返回null.
	 * 
	 * 需要 android.permission.ACCESS_FINE_LOCATION
	 * 
	 * @param context
	 *            the context
	 * @return 当期坐标
	 */
	public static Location getCurrentLocation(Context context) {
		LocationManager manager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		// from gps first
		Location location = manager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null) {
			Log.d(TAG, "No location info be found frmo GPS!");
		}
		// if not found from gps, then by net
		location = manager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (location == null) {
			Log.d(TAG, "No location info be found from NET!");
		}
		if (location != null) {
			Log.d(TAG,
					"GPS获取坐标:" + location.getLatitude() + ","
							+ location.getLongitude());
		}
		return location;
	}
}
