package com.fyp.halalrestaurants;

import java.util.ArrayList;
import java.util.HashMap;

import com.ccvb.android.yellowapi.model.Listing;
import com.ccvb.android.yellowapi.model.ModelComments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewsActivity extends Activity {
	EventDataSQLHelper eventsData;
	private ListView list;
	public ReviewsActivity ReviewsListView = null;

	public ArrayList<ModelComments> commentsArrayList = new ArrayList<ModelComments>();
	private ModelComments comment_obj;
	CommentsListAdapter reviews_adapter;
	private Button submit, go_to_login, logout, clear3, home_button;
	private TextView username;
	private EditText commentEntered, write_comment;
	private String returnedName;
	private String value="Reviews";
	private String[] comments_Data={};
	private String[] comments = {};
	private String[] user_names = {};
	private String[] comments_user_names = {};
	private String[] email_addresses = {};
	private String[] get_user_comments = {};
	private String email_id;
	private Integer res_id;
	UserSessionManager session;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {

	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reviews);
		write_comment = (EditText) findViewById(R.id.et_writereviews_reviews);
		home_button = (Button) findViewById(R.id.reviews_home_button);
		clear3 = (Button) findViewById(R.id.clear3);
		eventsData = new EventDataSQLHelper(this);
		go_to_login = (Button) findViewById(R.id.loginbutton);
		logout = (Button) findViewById(R.id.logoutbutton);
		username = (TextView) findViewById(R.id.tv_username_reviews);
		commentEntered = (EditText) findViewById(R.id.et_writereviews_reviews);
		submit = (Button) findViewById(R.id.submitbutton);
		logout.setVisibility(View.INVISIBLE);

		Bundle extras = this.getIntent().getExtras();
		res_id = extras.getInt("res_id");

		home_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ReviewsActivity.this,SearchRestaurant.class);
				startActivity(intent);

			}
		});
		clear3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				write_comment.setText("");
			}
		});

		String status = UserSessionManager.GetPreferencesstatus(
				Constant.User_STATUS, this);
		if (status.equals("Yes")) {

			go_to_login.setVisibility(View.INVISIBLE);
			submit.setVisibility(View.VISIBLE);
			logout.setVisibility(View.VISIBLE);

			email_id = UserSessionManager
					.GetPreferences(Constant.User_ID, this);

			String name = eventsData.getLoggedinUserName(email_id);

			username.setText(name);
			submit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					String passcomments = commentEntered.getText().toString();
					eventsData.insertComments(passcomments, email_id, res_id);
					 comments=eventsData.getComments(res_id);
					 email_addresses=eventsData.getAddresses(res_id);
					 user_names=eventsData.getUserNames(email_addresses);

					Intent reviews_intent = new Intent(ReviewsActivity.this,ReviewsActivity.class);
					reviews_intent.putExtra("res_id", res_id);
					startActivity(reviews_intent);
				}
			});

			comments = eventsData.getComments(res_id);
			email_addresses = eventsData.getAddresses(res_id);
			user_names = eventsData.getUserNames(email_addresses);

			 ReviewsListView = this;
			 setListData();
			 Resources resourses_reviews = getResources();
			 list = (ListView) findViewById(R.id.reviews_list);
			 reviews_adapter = new
			 CommentsListAdapter(ReviewsListView,commentsArrayList, resourses_reviews);
			 list.setAdapter(reviews_adapter);
			
			logout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					 setListData();
					 Resources resourses_reviews = getResources();
					 list = (ListView) findViewById(R.id.reviews_list);
					 reviews_adapter = new
					 CommentsListAdapter(ReviewsListView,commentsArrayList, resourses_reviews);
					 list.setAdapter(reviews_adapter);
					
					String userStatus = "No";
					Intent intent = new Intent(ReviewsActivity.this,
							ReviewsActivity.class);

					UserSessionManager.SaveUserStatus(Constant.User_STATUS,
							userStatus, getApplicationContext());
					intent.putExtra("res_id", res_id);
					startActivity(intent);

				}
			});

		}

		else
			go_to_login.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent login_intent = new Intent(ReviewsActivity.this,	LoginActivity.class);
                 	login_intent.putExtra("res_id", res_id);
                 	login_intent.putExtra("screen", value);
					startActivity(login_intent);
				}
			});

	}

//	 public void setListData(){
//		int count=0;int k=count;
//		do{
//		 for(int j=0; j< email_addresses.length;j++){
//			 
//			 String user_email=email_addresses[j];
//			 
//			    get_user_comments=eventsData.getuserComments(user_email);
//			    if(get_user_comments.length>=1){
//				   int i;
//				     for ( i = 0; i < get_user_comments.length; i++) {
//			    	 
//			    
//				      comment_obj = new ModelComments();
//				      comment_obj.setUserName(user_names[j]);
//				      comment_obj.setUserComment(comments[k]);
//				      commentsArrayList.add(comment_obj);
//			          k++;
//			     
//			          }
//			          count=i;
//	
//	            }
//
//             }
//         }
//		while(k<comments.length);
//	 }
	public void setListData(){
		int i;
		 comments_Data=eventsData.getComments_Data(res_id);
		 String[] new_CommentsArray=new String[comments_Data.length];
		 String[] new_EmailsArray=new String[comments_Data.length];
		 String[] splittedResults = new String[2];
		 for(i=0;i<comments_Data.length;i++){
		 splittedResults=comments_Data[i].split(",");
		
		 new_EmailsArray[i]=splittedResults[0];
		 new_CommentsArray[i]=splittedResults[1];
		 }
		 comments_user_names=eventsData.getNames_of_Comments( res_id, new_EmailsArray );
		 for(int k=0;k<comments_Data.length;k++){
			 comment_obj=new ModelComments();
			 comment_obj.setUserName(comments_user_names[k]);
			 comment_obj.setUserComment(new_CommentsArray[k]);
			 commentsArrayList.add(comment_obj);
		 }
			
	}
	
}

