package com.fyp.halalrestaurants;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantActivity extends Activity implements OnRatingBarChangeListener{
	EventDataSQLHelper eventsData;
	private String res_name;
	private String res_address;
	private Float latitude;
	String lat, lng;
	private Float longitude;
	private Integer res_id;
	private String screen_value="Restaurant Details";
    private Float[] rating_values;
	private Button clickmap, move_home, rate_me, review_button,login,logout;
	private RatingBar getRatingBar, setRatingBar;
	private String ItemSelectedMessageTemplate;
	private Float curRate;
	private String username;
	private Float newUserRate;
	private Float set_RateValue;
	private Float new_values=0f;
	private String email_id;
	private int count;
	private String[] rating_emails={};
	private TextView restaurant_name, restaurant_address, restaurant_city,
			restaurant_state, restaurant_postalcode, restaurant_phone;

	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);

		this.setContentView(R.layout.restaurant);

		eventsData = new EventDataSQLHelper(this);
		restaurant_name = (TextView) findViewById(R.id.res_name);
		restaurant_address = (TextView) findViewById(R.id.tv_res_address);
		restaurant_city = (TextView) findViewById(R.id.tv_res_city);
		restaurant_state = (TextView) findViewById(R.id.tv_res_state);
		restaurant_postalcode = (TextView) findViewById(R.id.tv_res_postalCode);
		restaurant_phone = (TextView) findViewById(R.id.tv_res_phone);

		clickmap = (Button) findViewById(R.id.show_map);
		login = (Button) findViewById(R.id.details_loginbutton);
		logout = (Button) findViewById(R.id.details_logoutbutton);
		
		review_button = (Button) findViewById(R.id.res_reviewsbutton);
		getRatingBar = (RatingBar) findViewById(R.id.getRating);
		setRatingBar = (RatingBar) findViewById(R.id.setRating);
		
		

		rate_me=(Button) findViewById(R.id.res_ratebutton);
//		setRatingBar.setRating(curRate);

		Bundle extras = this.getIntent().getExtras();
		res_id = extras.getInt("res_id");

		Cursor c = eventsData.getRestaurantDetails(res_id);
		restaurant_name.setText(c.getString(1));

		restaurant_address.setText(c.getString(2));
		restaurant_city.setText(c.getString(3));
		restaurant_state.setText(c.getString(4));
		restaurant_postalcode.setText(c.getString(5));
		restaurant_phone.setText(c.getString(6));
		latitude = c.getFloat(7);
		longitude = c.getFloat(8);

		lat = Float.toString(latitude);
		lng = Float.toString(longitude);
		logout.setVisibility(View.INVISIBLE);

		
		/////////////////////////////////////////////////////////////////////////////
		
		
		
//		 move_home.setOnClickListener(new View.OnClickListener()
//		 {
//		
//		 public void onClick(View agr0)
//		 {
//		 // TODO Auto-generated method stub
//		
//		 Intent move_to_home=new Intent(RestaurantActivity.this,SearchRestaurant.class);
//		 startActivity(move_to_home);
//		
//		 }
//		
//		
//		 });
		
		
		getRatingBar.setEnabled(false);
		//setRatingBar.setRating( set_RateValue);
         
	
		
        rate_me.setEnabled(false);
		String status = UserSessionManager.GetPreferencesstatus(
				Constant.User_STATUS, this);
		if (status.equals("Yes")) {

			getRatingBar.setEnabled(true);
			setRatingBar.setEnabled(true);
			
			
			login.setVisibility(View.INVISIBLE);
			logout.setVisibility(View.VISIBLE);
		    rate_me.setEnabled(true);
		    email_id = UserSessionManager.GetPreferences(Constant.User_ID, this);
		 
         
		
		 rate_me.setOnClickListener(new OnClickListener() {
		
		 @Override
		 public void onClick(View v)
		 {
		 // TODO Auto-generated method stub
		 newUserRate=getRatingBar.getRating();
		
		 
		 eventsData.update_Rating(newUserRate,email_id,res_id);
		 
		 rating_values=eventsData.getUsersRate(res_id);
		 for(int i=0;i<rating_values.length;i++){
		 new_values=new_values+rating_values[i];
		 }
		
		 rating_emails=eventsData.get_rating_Emails(res_id);
		 int no_of_users=rating_emails.length;
		 
		 set_RateValue=((new_values)/no_of_users);
		
		
		 setRatingBar.setRating( set_RateValue);
		 
         Intent reviews_intent = new Intent(RestaurantActivity.this,RestaurantActivity.class);
		 reviews_intent.putExtra("res_id", res_id);
		 startActivity(reviews_intent);
		 }
		 });
		 
		 logout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					
					String userStatus = "No";
					Intent intent = new Intent(RestaurantActivity.this,	RestaurantActivity.class);
					UserSessionManager.SaveUserStatus(Constant.User_STATUS,
							userStatus, getApplicationContext());
					intent.putExtra("res_id", res_id);
					startActivity(intent);

				}
			});
		}
		else
			
				login.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {

						Intent login_intent = new Intent(RestaurantActivity.this,	LoginActivity.class);
	                 	login_intent.putExtra("res_id", res_id);
	                 	login_intent.putExtra("screen", screen_value);
						startActivity(login_intent);
					}
				});
			
		 
		//login.setVisibility(View.INVISIBLE);
		
		 

	

		///////////////////////////////////////////////////////////////////////////////////////////////
		
		
		review_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View agr0) {
				// TODO Auto-generated method stub

				Intent move_to_reviews = new Intent(RestaurantActivity.this,ReviewsActivity.class);
				move_to_reviews.putExtra("res_id", res_id);
				startActivity(move_to_reviews);

			}

		});

		clickmap.setOnClickListener(new View.OnClickListener() {

			public void onClick(View agr0) {
				// TODO Auto-generated method stub
				res_name = restaurant_name.getText().toString();
				res_address = restaurant_address.getText().toString();
				Intent move_to_maps = new Intent(RestaurantActivity.this,
						GoogleMaps.class);
				move_to_maps.putExtra("res_name", res_name);
				move_to_maps.putExtra("res_address", res_address);
				Log.e("res_name", res_name);
				move_to_maps.putExtra("lat", lat);
            	move_to_maps.putExtra("lng", lng);
				startActivity(move_to_maps);

			}

		});

	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		// TODO Auto-generated method stub
		  DecimalFormat decimalFormat = new DecimalFormat("#.#");
		 curRate=Float.valueOf(decimalFormat.format((curRate * count + rating)/++ count));
		//Toast.makeText(RestaurantActivity.this,"New Rating: " + curRate, Toast.LENGTH_SHORT).show();
		 setRatingBar.setRating(curRate);
	}
}
