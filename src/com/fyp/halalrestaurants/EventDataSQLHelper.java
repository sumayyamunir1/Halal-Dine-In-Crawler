package com.fyp.halalrestaurants;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import com.ccvb.android.yellowapi.model.Listing;
import com.ccvb.android.yellowapi.model.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EventDataSQLHelper extends SQLiteOpenHelper {
	EventDataSQLHelper eventsData;
	Listing get_merchants_data;
	Restaurant get_web_data;
	private Cursor my_cursor;
	// /////////////////////////////////////////////////////
	private static final String DATABASE_NAME = "Halal.db";// database name
	private static final int DATABASE_VERSION = 1;// database version
	public SQLiteDatabase db;

	// //////////////////////////////table names//////////////////////////////
	public static final String TABLE_LOGIN = "LOGIN";
	public static final String TABLE_COMMENTS = "Comments_Table";
	public static final String RESTAURANTS = "Restaurants";
	public static final String TABLE_RATE = "Rate_Table";

	/////////////////////////////// table fields names//////////////////////////
	public static final String ID = "ID";
	public static final String FIRST_NAME = "FirstName"; // 1st column
	public static final String EMAIL = "Email";// 2rd column
	public static final String PASSWORD = "Password"; // 3rth column

	// /////////////////////////////////////////////////////////

	public static final String COMMENTS = "Comments";// 4th column comment
	////////////////////////////////////////////////////////////
	public static final String RATINGS = "Ratings";

	////////////////////////////////////////////////////////////

	public static final String RESTAURANT_ID = "RestaurantId";
	public static final String RESTAURANT_NAME = "RestaurantName";
	public static final String RESTAURANT_ADDRESS = "RestaurantAddress";
	public static final String RESTAURANT_CITY = "RestaurantCity";
	public static final String RESTAURANT_STATE = "RestaurantState";
	public static final String RESTAURANT_POSTALCODE = "RestaurantPostalcode";
	public static final String RESTAURANT_PHONE = "RestaurantPhone";
	public static final String RESTAURANT_LATITUDE = "RestaurantLatitude";
	public static final String RESTAURANT_LONGITUDE = "RestaurantLongitude";

	// ///////////////////////////////////////////////////

	public EventDataSQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public EventDataSQLHelper open() {
		db = this.getWritableDatabase();
		return this;
	}

	// function to create tables in a Halal Database
	public void onCreate(SQLiteDatabase db) {
		
        ////////////////////////RESTAURANT TABLE///////////////////
		String restaurant = " Create TABLE " + RESTAURANTS + "(" + RESTAURANT_ID
				+ " int Primary key not null, " + RESTAURANT_NAME + " text,  "
				+ RESTAURANT_ADDRESS + " text, " + RESTAURANT_CITY + " text, "
				+ RESTAURANT_STATE + " text, " + RESTAURANT_POSTALCODE
				+ " text,  " + RESTAURANT_PHONE + " int ,  "
				+ RESTAURANT_LATITUDE + " Float, " + RESTAURANT_LONGITUDE
				+ " Float ); ";
		Log.d("EventsData", "onCreate:" + restaurant);
		db.execSQL(restaurant);

		///////////////////////LOGIN TABLE//////////////////////////
		String login = " CREATE TABLE " + TABLE_LOGIN + "(" + FIRST_NAME
				+ " text, " + EMAIL + " text Primary key not null, " + PASSWORD
				+ " text);";
		Log.d("EventsData", "onCreate:" + login);
		db.execSQL(login);
		
        ////////////////////////COMMENTS TABLE//////////////////////
		String comment_table = " Create TABLE " + TABLE_COMMENTS + "("
				+ RESTAURANT_ID + " int , " + EMAIL + " text , " + COMMENTS
				+ " text , " + " FOREIGN KEY (" + RESTAURANT_ID
				+ " ) REFERENCES " + RESTAURANTS + " ( " + RESTAURANT_ID
				+ " ) " + " FOREIGN KEY ( " + EMAIL + " ) REFERENCES "
				+ TABLE_LOGIN + " ( " + EMAIL + " )) ; ";
		Log.d("EventsData", "onCreate:" + comment_table);
		db.execSQL(comment_table);
		
		////////////////////////RATE TABLE/////////////////////////
		
		String rate_table = " Create TABLE " + TABLE_RATE + "("
				+ RESTAURANT_ID + " int , " + EMAIL + " text , " + RATINGS
				+ " Float , " + " FOREIGN KEY (" + RESTAURANT_ID
				+ " ) REFERENCES " + RESTAURANTS + " ( " + RESTAURANT_ID
				+ " ) " + " FOREIGN KEY ( " + EMAIL + " ) REFERENCES "
				+ TABLE_LOGIN + " ( " + EMAIL + " )) ; ";
		Log.d("EventsData", "onCreate:" + rate_table);
		db.execSQL(rate_table);
	}

	// function to upgrade the Database
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// if(oldVersion>=newVersion)
		// return;
		String res = null;
		String sql_rating = null;

		String sql = null;
		String comment_table = null;
		// if(oldVersion==1)
		// sql="alter table" + TABLE_NAME + "add note text;";
		// if(oldVersion==2)
		// sql="";
		// Log.d("EventsData","onUpgrade:"+sql);
		// if(sql!=null)
		db.execSQL(sql);
		db.execSQL(comment_table);
		db.execSQL(res);
		db.execSQL(sql_rating);
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	///////////////// inserting values in database table Login//////////////////////////
	
	public void insert(String username, String email, String password) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		// Assigning values for each row
		values.put("Password", password);
		values.put("FirstName", username);
		values.put("Email", email);
		db.insert("LOGIN", null, values);
		db.close();
	}

	////////////////////////////////////////////////////////////////////////////////////
	//////////////////// function to insert comments in a Database Table Comments//////////
	
	public void insertComments(String passcomments, String email_id,
			Integer res_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		// assign values to column comments
		values.put("Email", email_id);
		values.put("RestaurantId", res_id);
		values.put("Comments", passcomments);
		db.insert("Comments_Table", null, values);
		db.close();

	}

	////////////////////////////////////////////////////////////////////////////////////
	
	public void insertRatings(Float newUserRate,String email_id,Integer res_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Email", email_id);
		values.put("RestaurantId", res_id);
		values.put("Ratings", newUserRate);
		db.insert("Rate_Table", null, values);

	}
	

	////////////////////////////////////////////////////////////////////////////////////
	
	public void insertWebData(ArrayList<Restaurant> webDataList) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < webDataList.size(); i++) {
			get_web_data = webDataList.get(i);
			values.put(RESTAURANT_ID, get_web_data.getId());
			values.put(RESTAURANT_NAME, get_web_data.getName());
			values.put(RESTAURANT_ADDRESS, get_web_data.getAddress());
			values.put(RESTAURANT_CITY, get_web_data.getCity());
			values.put(RESTAURANT_STATE, get_web_data.getState());
			values.put(RESTAURANT_POSTALCODE, get_web_data.getPostalCode());
			values.put(RESTAURANT_PHONE, get_web_data.getPhone());
			values.put(RESTAURANT_LATITUDE, get_web_data.getLatitude());
			values.put(RESTAURANT_LONGITUDE, get_web_data.getLongitude());
			db.insert("Restaurants", null, values);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
    ///////////function to check whether the database is empty or not//////////////////
	
	public boolean checkDB() {

		String sqlQuery = "SELECT * FROM Restaurants";
		Cursor mycursor = getReadableDatabase().rawQuery(sqlQuery, null);

		if (mycursor.getCount() > 0) {
			return true;
		} else {
			return false;

		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////
	///////////////// function to get user password when username exist in a database/////////
	
	public String getUsername(String usernameEntered) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query("LOGIN", null, "FirstName=?",
				new String[] { usernameEntered }, null, null, null);

		if (cursor.getCount() < 1) {
			cursor.close();
			return "NOT EXIST";
		} else

			cursor.moveToFirst();
		String password = cursor.getString(cursor.getColumnIndex("Password"));
		cursor.close();
		return password;

	}
    
	///////////////////////////////////////////////////////////////////////////////////
	////////////////////get email addresses of all the users///////////////////////////
	
	public Boolean getEmailAddress(String emailEntered) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery = "select EMAIL from LOGIN where EMAIL= '"
				+ emailEntered + "'";
		Cursor cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return true;

		} else

			return false;
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	/////////////////////////function to get all restaurant names/////////////////////
	
	public String[] getRestaurantNames() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String[] restaurants_array;
		int i = 0;

		String query = "Select RestaurantName from Restaurants";
		// Log.e("query",query);
		Cursor my_cursor = getReadableDatabase().rawQuery(query, null);
		my_cursor.moveToFirst();
		restaurants_array = new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {
			restaurants_array[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}
		my_cursor.close();
		return restaurants_array;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	///////////////////////function to get all the states///////////////////////////////
	
	public String[] getStates() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getReadableDatabase();
		String[] states_array;
		String[] states_newArray = {};
		int i = 0;
		int k;
		int cnt = 0;
		List<String> uniqueWords = new ArrayList<String>();
		String query = "Select RestaurantState from Restaurants";
		Cursor my_cursor = getReadableDatabase().rawQuery(query, null);
		my_cursor.moveToFirst();
		states_array = new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {
			states_array[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}
		for (k = 0; k < states_array.length; k++) {
			for (int j = k + 1; j < states_array.length; j++) {
				if (states_array[k].equals(states_array[j])) {
					cnt += 1;

				}
			}
			if (cnt < 1) {
				uniqueWords.add(states_array[k]);
				states_newArray = uniqueWords.toArray(new String[uniqueWords
						.size()]);

			}
			cnt = 0;

		}

		my_cursor.close();
		return states_newArray;
	}

	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////function to get ratings/////////////////////////////////
	
	 public Float[] getUsersRate(Integer res_id)
	 {
	 int i=0;
	 SQLiteDatabase db = this.getReadableDatabase();
	 Float[] array_ratings;
	 String query="Select Ratings from Rate_Table WHERE RestaurantId= '" + res_id + "'";
	 Cursor my_cursor=getReadableDatabase().rawQuery(query, null);
	 my_cursor.moveToFirst();
	 array_ratings=new Float[my_cursor.getCount()];
	 while(!my_cursor.isAfterLast())
	 {
	 array_ratings[i]=my_cursor.getFloat(0);
	 my_cursor.moveToNext();
	 i++;
	 }
	 my_cursor.close();
	 return array_ratings;
	
	 }
	 
		////////////////////////////////////////////////////////////////////////////////////
	 ///////////////////////////////function to get existing restaurants data///////////////
	 
	 public ArrayList<Restaurant> getExistingData() {
		Restaurant res = new Restaurant();
		ArrayList<Restaurant> dbData = new ArrayList<Restaurant>();
		String sqlQuery = "SELECT * FROM Restaurants";
		Cursor cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		cursor.moveToFirst();
		if (!cursor.isAfterLast()) {
			do {
				res.setId(cursor.getString(0));
				res.setName(cursor.getString(1));
				res.setAddress(cursor.getString(2));
				res.setCity(cursor.getString(3));
				res.setState(cursor.getString(4));
				res.setPostalCode(cursor.getString(5));
				res.setPhone(cursor.getString(6));

				res.setLatitude(cursor.getString(7));
				res.setLatitude(cursor.getString(8));

				dbData.add(res);

			} while (cursor.moveToNext());
		}
		cursor.close();
		return dbData;

	}

    ////////////////////////////////////////////////////////////////////////////////////////// 
	//////////////////////function to update the database with new fetched results////////////
	 
	public void update(ArrayList<Restaurant> parsedWebList,
			EventDataSQLHelper eventsData) {
		SQLiteDatabase db = eventsData.getWritableDatabase();
		String query = null;
		Boolean dbValue = checkDB();
		if (dbValue == false) {
			insertWebData(parsedWebList);
		} else

			// ContentValues values=new ContentValues();
			for (int j = 0; j < parsedWebList.size(); j++) {
				// Log.e("ID==", msg);
				query = "UPDATE" + " " + EventDataSQLHelper.RESTAURANTS + " "
						+ "SET" + " " + EventDataSQLHelper.RESTAURANT_NAME
						+ "='" + parsedWebList.get(j).getName() + "',"
						+ EventDataSQLHelper.RESTAURANT_ADDRESS + "='"
						+ parsedWebList.get(j).getAddress() + "',"
						+ EventDataSQLHelper.RESTAURANT_CITY + "='"
						+ parsedWebList.get(j).getCity() + "',"
						+ EventDataSQLHelper.RESTAURANT_STATE + "='"
						+ parsedWebList.get(j).getState() + "',"
						+ EventDataSQLHelper.RESTAURANT_POSTALCODE + "='"
						+ parsedWebList.get(j).getPostalCode() + "',"
						+ EventDataSQLHelper.RESTAURANT_PHONE + "='"
						+ parsedWebList.get(j).getPhone() + "',"
						+ EventDataSQLHelper.RESTAURANT_LATITUDE + "='"
						+ parsedWebList.get(j).getLatitude() + "',"
						+ EventDataSQLHelper.RESTAURANT_LONGITUDE + "='"
						+ parsedWebList.get(j).getLongitude() + "' " + "WHERE"
						+ " " + EventDataSQLHelper.RESTAURANT_ID + "='"
						+ parsedWebList.get(j).getId() + "';";
				// Log.e("query",query);
			}
		db.execSQL(query);
	}

	////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////function to get email id of loggedin user////////////////
	
	public String getLoggedinUserID(String usernameEntered,
			String userpasswordEntered) {
		String sqlQuery = "Select Email from LOGIN where FirstName='"
				+ usernameEntered + "'" + " AND Password ='"
				+ userpasswordEntered + "'";
		String email_id = "";
		Cursor mycursor = getReadableDatabase().rawQuery(sqlQuery, null);
		mycursor.moveToFirst();
		if (mycursor.getCount() > 0) {
			do {

				email_id = mycursor.getString(mycursor.getColumnIndex("Email"));

			} while (mycursor.moveToNext());
		}
		mycursor.close();
		return email_id;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////function to get name of loggedin user/////////////////////////
	
	public String getLoggedinUserName(String email_id) {
		// TODO Auto-generated method stub
		String name = "";
		String sqlQuery = "SELECT FirstName FROM LOGIN WHERE Email= '"
				+ email_id + "'";
		Cursor mycursor = getReadableDatabase().rawQuery(sqlQuery, null);
		if (mycursor.moveToFirst()) {
			do {

				name = mycursor.getString(mycursor.getColumnIndex("FirstName"));

			} while (mycursor.moveToNext());
		}
		return name;
	}

	
	////////////////////////////////////////////////////////////////////////////////////
    //////////////////////function to get all the comments against one restaurant///////	
	
	public String[] getComments(Integer res_id) {
		int i = 0;
		String[] comments = {};
		String sqlQuery = "SELECT Comments FROM Comments_table WHERE RestaurantId= '"
				+ res_id + "'";
		Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		my_cursor.moveToFirst();
		comments = new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {

			comments[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}

		my_cursor.close();
		return comments;
	}

	////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////function to get email ids against one restaurant//////////////
	
	public String[] getAddresses(Integer res_id) {

		List<String> uniqueWords = new ArrayList<String>();
		int cnt = 0;
		int i = 0;
		String[] email_addresses = {};
		String[] email_newaddresses = {};
		String sqlQuery = "SELECT Email FROM Comments_table WHERE RestaurantId= '"
				+ res_id + "'";
		Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		my_cursor.moveToFirst();

		email_addresses = new String[my_cursor.getCount()];

		while (!my_cursor.isAfterLast()) {

			email_addresses[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
 			i++;                                  
		}
		for (i = 0; i < email_addresses.length; i++) {  			
			for (int j = i + 1; j < email_addresses.length; j++) {

  				if (email_addresses[i].equals(email_addresses[j])) {
					cnt += 1;

				}
			}
			if (cnt < 1) {
				uniqueWords.add(email_addresses[i]);
				email_newaddresses = uniqueWords.toArray(new String[uniqueWords
						.size()]);

			}
			cnt = 0;

		}
		my_cursor.close();
		return email_newaddresses;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////function to get usernames against email addresses/////////////
	
	public String[] getUserNames(String[] email_addresses) {
		String[] user_names = new String[email_addresses.length];
		
        int k=0;
		for (int i = 0; i < email_addresses.length; i++) {
			String sqlQuery = "SELECT FirstName FROM LOGIN WHERE Email= '"
					+ email_addresses[i] + "'";
			Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
			if (my_cursor != null && my_cursor.getCount() > 0) {
				while (my_cursor.moveToNext()) {
        		user_names[i] = my_cursor.getString(0);
				} 

			}
			my_cursor.close();
		}

		return user_names;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////function to get comments against one email address/////////
	
	public String[] getuserComments(String user_email) {
		// TODO Auto-generated method stub
		int i=0;
		String[] user_comments={};
		SQLiteDatabase db = this.getReadableDatabase();
		 String sqlQuery = "SELECT Comments FROM Comments_table WHERE Email= '" + user_email + "'";
		
		 Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
			my_cursor.moveToFirst();
			user_comments = new String[my_cursor.getCount()];
			while (!my_cursor.isAfterLast()) {

				user_comments[i] = my_cursor.getString(0);
				my_cursor.moveToNext();
				i++;
			}

			my_cursor.close();
			return user_comments;
	}
	
	
	
    ///////////////////////////function to get restaurant id of a selected restaurant////////////
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public Integer getRestaurantID(String restaurant) {
		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery = "SELECT RestaurantId FROM Restaurants WHERE RestaurantName= \""
				+ restaurant + "\"";
		int res_id;
		Cursor cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		if (cursor.moveToFirst())
			;
		do {
			res_id = cursor.getInt(0);

		} while (cursor.moveToNext());

		cursor.close();
		return res_id;
	}

	
	///////////////////////////////////////////////////////////////////////////////////////
    /////////////////////function to get restaurant details of a selected restaurant///////	
	
	public Cursor getRestaurantDetails(Integer res_id) {

		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery = "SELECT * FROM Restaurants WHERE RestaurantID= '"
				+ res_id + "'";
		Cursor cursor = getReadableDatabase().rawQuery(sqlQuery, null);

		cursor.moveToFirst();

		return cursor;
	}

	
	////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////function to get restaurant names of a selected state////////////
	
	public String[] getRestaurantsForState(String state) {
		int i = 0;
		String[] res_names = {};
		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery = "SELECT RestaurantName FROM Restaurants WHERE RestaurantState= '"
				+ state + "'";
		Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		my_cursor.moveToFirst();
		res_names = new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {
			res_names[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}
		my_cursor.close();
		return res_names;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////function to restaurant address of a selected state////////////
	public String[] getRestaurantAddressForState(String state) {
		int i = 0;
		String[] res_address = {};
		SQLiteDatabase db = this.getReadableDatabase();
		String sqlQuery = "SELECT RestaurantAddress FROM Restaurants WHERE RestaurantState= '"
				+ state + "'";
		Cursor my_cursor = getReadableDatabase().rawQuery(sqlQuery, null);
		my_cursor.moveToFirst();
		res_address = new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {
			res_address[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}
		my_cursor.close();
		return res_address;
	}

	public String[] get_rating_Emails(Integer res_id) {
		// TODO Auto-generated method stub
		int i=0;
		String[] rating_emails={};
		SQLiteDatabase db=this.getReadableDatabase();
		String sqlQuery="SELECT Email from Rate_Table WHERE Ratings IS NOT NULL AND RestaurantID= '" + res_id + "'";
		Cursor my_cursor=getReadableDatabase().rawQuery(sqlQuery, null);
		my_cursor.moveToFirst();
		rating_emails=new String[my_cursor.getCount()];
		while (!my_cursor.isAfterLast()) {
			rating_emails[i] = my_cursor.getString(0);
			my_cursor.moveToNext();
			i++;
		}
		my_cursor.close();
		return rating_emails;
		
		
	}
	

	public void update_Rating(Float newUserRate, String email_id,Integer res_id) {
		// TODO Auto-generated method stub
		String query = null;
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete("Rate_Table", "Email = ?", new String[] {email_id});
		insertRatings(newUserRate,email_id,res_id);
	}

	public String[] getComments_Data(Integer res_id) {
		// TODO Auto-generated method stub
		int i=0;
		 String[] comments={};
		 String[] email_addresses={};
		 String[] new_Data={};
		SQLiteDatabase db=this.getReadableDatabase();
		String query="SELECT * FROM Comments_Table WHERE RestaurantId= '"+ res_id + "'";
		Cursor my_cursor = getReadableDatabase().rawQuery(query, null);
	//	my_cursor.moveToFirst();
		new_Data=new String[my_cursor.getCount()];
		 email_addresses=new String[my_cursor.getCount()];
		 comments=new String[my_cursor.getCount()];
		while (my_cursor.moveToNext()) {

			 email_addresses[i] = my_cursor.getString(1);
			comments[i]=my_cursor.getString(2);
		
			Log.e("Email Address",email_addresses[i]); 
			new_Data[i]=email_addresses[i]+","+comments[i]; 
			i++;	
		}
		
		
		my_cursor.close();
		return new_Data;
		
	}

	public String[] getNames_of_Comments(Integer res_id, String[] new_EmailsArray) {
		// TODO Auto-generated method stub
		String[] user_names = new String[new_EmailsArray.length];
		for(int j=0;j < new_EmailsArray.length;j++){
		String query="SELECT FirstName FROM LOGIN WHERE Email= '"	+ new_EmailsArray[j] + "'";
		Cursor my_cursor=getReadableDatabase().rawQuery(query, null);
		if(my_cursor!=null && my_cursor.getCount()>0){
			while(my_cursor.moveToNext()){
			user_names[j]=my_cursor.getString(0);
		    }
	
		
		}
		my_cursor.close();
				
		
	}
		return user_names;

	/////////////////////////////////////////////////////////////////////////////////////////////	

}

	public String[] getRestaurantsReviews() {
		// TODO Auto-generated method stub
		int j=0;
		String[] new_Array={};
		String[] comments={};
		String[] resIds={};
		String query="SELECT Comments AND RestaurantID FROM Comments_Table";
		Cursor my_cursor=getReadableDatabase().rawQuery(query, null);
		comments=new String[my_cursor.getCount()];
		resIds=new String[my_cursor.getCount()];
		if(my_cursor!=null&&my_cursor.getCount()>0){
			while(my_cursor.moveToNext()){
				comments[j]=my_cursor.getString(1);
				resIds[j]=my_cursor.getString(2);
				new_Array[j]=comments[j]+","+resIds[j];
				j++;
			}
			
		}
		my_cursor.close();
		return new_Array;
		
	}
}
