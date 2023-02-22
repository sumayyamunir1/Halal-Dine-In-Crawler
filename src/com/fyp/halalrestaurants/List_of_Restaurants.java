/*
 Copyright © 2011, Yellow Pages Group Co.  All rights reserved.
 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 1)	Redistributions of source code must retain a complete copy of this notice, including the copyright notice, this list of conditions and the following disclaimer; and
 2)	Neither the name of the Yellow Pages Group Co., nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT OWNER AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.fyp.halalrestaurants;

import java.util.ArrayList;

import android.app.Activity;

import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.ListView;

import com.ccvb.android.yellowapi.model.Listing;

public class List_of_Restaurants extends Activity {
	EventDataSQLHelper eventsData;
	private ListView list;
	public List_of_Restaurants CustomListView = null;

	Activity context;
	private Button home_button;
	public ArrayList<Listing> restaurantArrayList = new ArrayList<Listing>();
	private String[] res_names = {};
	private String[] res_address = {};
	RestaurantListAdapter restaurant_list_adapter;
	private Listing listing;
	private int res_id;

	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);

		eventsData = new EventDataSQLHelper(this);
		setContentView(R.layout.list_of__restaurants);
		home_button = (Button) findViewById(R.id.home_button);

		Bundle b = this.getIntent().getExtras();
		res_names = b.getStringArray("res_names");
		res_address = b.getStringArray("res_address");

		CustomListView = this;
		setListData();

		Resources res = getResources();
		list = (ListView) findViewById(R.id.restaurants_list);

		restaurant_list_adapter = new RestaurantListAdapter(CustomListView,
				restaurantArrayList, res);
		list.setAdapter(restaurant_list_adapter);

		home_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View agr0) {
				// TODO Auto-generated method stub

				Intent move_to_home = new Intent(agr0.getContext(),
						SearchRestaurant.class);
				startActivity(move_to_home);

			}
		});

	}

	public void setListData() {
		for (int i = 0; i < res_names.length; i++) {
			listing = new Listing();
			listing.setName(res_names[i]);
			listing.setAddress(res_address[i]);
			restaurantArrayList.add(listing);
		}

	}

	public void onItemClick(int mPosition) {
		Listing tempValues = (Listing) restaurantArrayList.get(mPosition);
		String restaurant = tempValues.getName();
		res_id = eventsData.getRestaurantID(restaurant);
		Intent myIntent = new Intent(List_of_Restaurants.this,
				RestaurantActivity.class);
		myIntent.putExtra("res_id", res_id);
		startActivity(myIntent);

	}

}