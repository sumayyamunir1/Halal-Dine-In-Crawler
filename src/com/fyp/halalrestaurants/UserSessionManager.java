package com.fyp.halalrestaurants;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserSessionManager {

	UserSessionManager session;
	// Shared Preferences reference
	static SharedPreferences pref;

	// Editor reference for Shared preferences
	static Editor editor;
	// Context
	private static Context _context;
	static Editor editor_status;
	private static int value;

	public static void SavePreferences(String userId, String email_id,
			Context context) {
		// TODO Auto-generated method stub
		_context = context;
		pref = _context.getSharedPreferences(Constant.PREF_NAME,
				Constant.PRIVATE_MODE);
		editor = pref.edit();
		editor.putString(userId, email_id);
		editor.commit();

	}

	public static void SaveUserStatus(String userStatus, String status_value,
			Context context) {
		// TODO Auto-generated method stub
		_context = context;
		pref = _context.getSharedPreferences(Constant.PREF_NAME,
				Constant.PRIVATE_MODE);
		editor = pref.edit();
		editor.putString(userStatus, status_value);
		editor.commit();

	}

	public static String GetPreferences(String key, Context context) {
		_context = context;
		pref = _context.getSharedPreferences(Constant.PREF_NAME,
				Constant.PRIVATE_MODE);
		String savedPreferences = pref.getString(key, "");
		return savedPreferences;
	}

	public static String GetPreferencesstatus(String key, Context context) {
		_context = context;
		pref = _context.getSharedPreferences(Constant.PREF_NAME,
				Constant.PRIVATE_MODE);
		String savedPreferences = pref.getString(key, "");
		return savedPreferences;
	}

}
