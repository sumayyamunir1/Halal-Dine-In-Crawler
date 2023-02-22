package com.fyp.halalrestaurants;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {

	private final int SPLASH_DISPLAY_LIGHT = 50;

	// called when the activity is first created
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash);

		/*
		 * New Handler to start the Splash screen and close this Splash-Screen
		 * after some seconds.
		 */
		new Handler().postDelayed(new Runnable() {
			public void run() {
				// Create an Intent that will start the Menu-Activity.
				Intent splash_screen = new Intent(SplashScreen.this,
						SearchRestaurant.class);
				SplashScreen.this.startActivity(splash_screen);
				SplashScreen.this.finish();

			}
		}, SPLASH_DISPLAY_LIGHT);
	}

}
