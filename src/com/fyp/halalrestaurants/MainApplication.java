package com.fyp.halalrestaurants;

import android.app.Application;

public class MainApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		Constants.init(this.getApplicationContext());
	}
}