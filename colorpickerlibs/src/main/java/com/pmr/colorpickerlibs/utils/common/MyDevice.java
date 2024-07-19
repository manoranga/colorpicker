package com.pmr.colorpickerlibs.utils.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings.Secure;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class MyDevice {

	private final static String TAG = "MyDevice";

	public static boolean isAppInstalled(Context context, String uri) {
		PackageManager pm = context.getPackageManager();
		boolean app_installed;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		}
		catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	/**
	 * App is running or not
	 *
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static boolean isAppRunning(final Context context, final String packageName) {
		final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
		if (procInfos != null) {
			for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
				if (processInfo.processName.equals(packageName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void playNotificationSound(Context mContext) {
		try {
			Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
					+ "://" + mContext.getPackageName() + "/raw/notification");
			Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
			r.play();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	/**
	 * Is tablet
	 *
	 * @param context
	 * @return
	 */
	public static boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
		boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}

	/**
	 * Height inches
	 *
	 * @param activity
	 * @return
	 */
	public static double getInches(Activity activity) {
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

		int widthPixels = metrics.widthPixels;
		int heightPixels = metrics.heightPixels;

		double widthDpi = metrics.xdpi;
		double heightDpi = metrics.ydpi;

		double widthInches = widthPixels / widthDpi;
		double heightInches = heightPixels / heightDpi;

		double d = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
		return d;
	}

	public static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
		try {
			packageManager.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	/**
	 * Get application version
	 *
	 * @param context
	 * @return
	 */
	public static String getAppVersion(Context context) {
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			return "1.0.0";
		}
	}

	/**
	 * Get brand
	 *
	 * @return
	 */
	public static String getBrand() {
		String brand = Build.MANUFACTURER;
		if (brand == null || brand.isEmpty()) {
			brand = "B";
		}
		return brand;
	}

	/**
	 * Get model
	 *
	 * @return
	 */
	public static String getModel() {
		String model = Build.MODEL;
		if (model == null || model.isEmpty()) {
			model = "M";
		}
		return model;
	}

	/**
	 * Copy text to Clipboard
	 *
	 * @param context
	 * @param text
	 */
	public static void copyToClipboard(Context context, String text) {
		ClipboardManager cManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData cd = ClipData.newPlainText("TEXT", text);
		cManager.setPrimaryClip(cd);
	}

	public static String getTextFromClipboard(Context context) {
		ClipboardManager cManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		return cManager.getText().toString();
	}

	/**
	 * Get Device ID
	 *
	 * @param context
	 * @return Device ID
	 */
	public static String getDeviceId(Context context) {
		return Secure
				.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}




	/**
	 * Is Internet Available
	 * 
	 * @param context
	 * @return isInternetAvailable
	 */
	public static boolean isInternetOn(Context context) {
		/*
		 * Put this line to Manifest <uses-permission
		 * android:name="android.permission.ACCESS_NETWORK_STATE" />
		 */
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		@SuppressLint("MissingPermission") NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	/**
	 * Show the soft keyboard enabled for the current text editor.
	 * 
	 * @param context
	 */
	public static void showSoftKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	public static void hideKeyboard(Context context) {
		// Check if no view has focus:
		View view = ((Activity) context).getCurrentFocus();
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	/**
	 * Get screen dimensions of device
	 * 
	 * @param context
	 * @return Point
	 */
	public static Point getScreenDimensions(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		return size;
	}

	public static void generateHashKey(Context context){
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					"com.nationstrust.frimi",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());

				Log.e("KEY_HASH", "Package Name: " + info.packageName);
				Log.e("KEY_HASH", "Base 64 Name: " + Base64.encodeToString(md.digest(),
						Base64.NO_WRAP));
			}
		} catch (PackageManager.NameNotFoundException e) {
			Log.d("MyDevice", e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			Log.d("MyDevice", e.getMessage(), e);
		}
	}

	/**
	 * Method checks if the app is in background or not
	 */
	public static boolean isAppIsInBackground(Context context) {
		boolean isInBackground = true;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
			List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
			for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
				if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					for (String activeProcess : processInfo.pkgList) {
						if (activeProcess.equals(context.getPackageName())) {
							isInBackground = false;
						}
					}
				}
			}
		} else {
			List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
			ComponentName componentInfo = taskInfo.get(0).topActivity;
			if (componentInfo.getPackageName().equals(context.getPackageName())) {
				isInBackground = false;
			}
		}

		return isInBackground;
	}

	/**
	 * Get api level
	 * 
	 * @return
	 */
	public static Integer getApiLevel(){
		return Build.VERSION.SDK_INT;
	}

	public static boolean isTab(Context context){
		try {
			return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE ||
					(context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE;
		} catch (Exception e){
			return true;
		}
	}
}
