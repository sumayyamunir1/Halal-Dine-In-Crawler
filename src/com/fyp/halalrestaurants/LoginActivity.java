package com.fyp.halalrestaurants;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button login;
	EventDataSQLHelper eventsData;
	private String resDetails_value="Restaurant Details";
	private String reviews_value="Reviews";
	private String screen_value;
	private String reviews;
	
	private EditText username, userpassword;
	private TextView signup;
	private String usernameEntered, userpasswordEntered;
	private Integer res_id;
	UserSessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// session = new UserSessionManager(getApplicationContext());

		Bundle extras = this.getIntent().getExtras();
		screen_value= extras.getString("screen");
		

		res_id= extras.getInt("res_id");
		

		eventsData = new EventDataSQLHelper(this);
		username = (EditText) findViewById(R.id.et_name_login);
		userpassword = (EditText) findViewById(R.id.et_password_login);
		signup = (TextView) findViewById(R.id.regg);

		// Toast.makeText(getApplicationContext(),"User Login Status:" +
		// session.isUserLoggedIn(),Toast.LENGTH_LONG).show();
		login = (Button) findViewById(R.id.loginbutton);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View agr0) {
				// TODO Auto-generated method stub
				usernameEntered = username.getText().toString();
				userpasswordEntered = userpassword.getText().toString();
				String storedPassword = eventsData.getUsername(usernameEntered);

				// Validate if username, password is filled
				if (usernameEntered.trim().length() > 0
						&& userpasswordEntered.trim().length() > 0)

					// For testing purpose username with this password exist or2 not
					if (userpasswordEntered.equals(storedPassword)) {

						String email_id = eventsData.getLoggedinUserID(
								usernameEntered, userpasswordEntered);

						String userStatus = "Yes";
						if(screen_value.equals("Reviews")){
						Intent login = new Intent(LoginActivity.this,ReviewsActivity.class);
                        UserSessionManager.SavePreferences(Constant.User_ID,email_id, getApplicationContext());
						UserSessionManager.SaveUserStatus(Constant.User_STATUS,userStatus, getApplicationContext());
						login.putExtra("res_id", res_id);
						startActivity(login);
						}
						else if(screen_value.equals("Restaurant Details")){
						Intent restaurant_details = new Intent(LoginActivity.this,RestaurantActivity.class);
						UserSessionManager.SavePreferences(Constant.User_ID,email_id, getApplicationContext());
						UserSessionManager.SaveUserStatus(Constant.User_STATUS,userStatus, getApplicationContext());
						
						restaurant_details .putExtra("res_id", res_id);
						startActivity(restaurant_details);
					}
						
					}

					else {

						Toast.makeText(LoginActivity.this,
								"Username/Password is incorrect",
								Toast.LENGTH_LONG).show();
					}

				else {
					// user didn't entered username or password
					Toast.makeText(getApplicationContext(),
							"Please enter username and password",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent signup = new Intent(LoginActivity.this,
						SignupActivity.class);
				signup.putExtra("res_id", res_id);
				startActivity(signup);

			}
		});
	}

}



