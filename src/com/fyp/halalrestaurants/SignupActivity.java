package com.fyp.halalrestaurants;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import android.widget.TextView;

public class SignupActivity extends Activity {
	EventDataSQLHelper eventsData;

	private Button signup;
	private EditText passwordEntered;
	private EditText confirmpasswordEntered;
	private EditText usernameEntered;
	private Integer res_id;
	private EditText emailEntered;
	private String password, confirmpassword, username, email;
	
	public final Pattern EMAIL_ADDRESS_PATTERN = Pattern
			.compile("^\\D.+@.+\\.[a-z]+");
				@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);

		eventsData = new EventDataSQLHelper(this);

		passwordEntered = (EditText) findViewById(R.id.et_password_signup);
		confirmpasswordEntered = (EditText) findViewById(R.id.et_confirmpass_signup);
		usernameEntered = (EditText) findViewById(R.id.et_name_signup);
		emailEntered = (EditText) findViewById(R.id.et_email_signup);

		Bundle extras = this.getIntent().getExtras();
		res_id = extras.getInt("res_id");

		// emailEntered.addTextChangedListener(new TextWatcher() {
		//
		//
		// @Override
		// public void onTextChanged(CharSequence s, int start, int before, int
		// count) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void beforeTextChanged(CharSequence s, int start, int count,
		// int after) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void afterTextChanged(Editable s) {
		// // TODO Auto-generated method stub
		// if(emailEntered.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")&&
		// s.length() > 0){
		// emailEntered.setText("valid email");
		// }
		// else
		// {
		// emailEntered.setText("invalid email");
		// }
		// }
		//
		// });
		// Click button
		signup = (Button) findViewById(R.id.signup);
		signup.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View agr0) {

				password = passwordEntered.getText().toString();
				confirmpassword = confirmpasswordEntered.getText().toString();

				if (password.equals(confirmpassword)) {
					// after confirm password insert this data into database
					username = usernameEntered.getText().toString();

					// last=lastnameEntered.getText().toString();
					email = emailEntered.getText().toString();
					if (!email.equals("")) {
						if (checkEmail(email)) {

							Boolean storedEmailAddress = eventsData
									.getEmailAddress(email);
							if (storedEmailAddress == true) {
								eventsData.insert(username, email, password);
								Intent loginscreen = new Intent(SignupActivity.this,LoginActivity.class);
								loginscreen.putExtra("res_id", res_id);
								startActivity(loginscreen);
							} else {
								Toast.makeText(SignupActivity.this,
										"Account Exits ", Toast.LENGTH_LONG)
										.show();
							}
						} else
							Toast.makeText(SignupActivity.this,
									"Invalid Email Addresss",
									Toast.LENGTH_SHORT).show();

					}
				} else {
					// show this message to user that password does not match

					Toast.makeText(SignupActivity.this,
							"Password does not match ", Toast.LENGTH_LONG)
							.show();

				}

			}
		});

	}

	private boolean checkEmail(String email) {
		return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
	}
}
