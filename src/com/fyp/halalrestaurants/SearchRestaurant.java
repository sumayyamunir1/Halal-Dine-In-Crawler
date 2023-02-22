/*
Copyright © 2011, Yellow Pages Group Co.  All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1)	Redistributions of source code must retain a complete copy of this notice, including the copyright notice, this list of conditions and the following disclaimer; and
2)	Neither the name of the Yellow Pages Group Co., nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT OWNER AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.fyp.halalrestaurants;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ccvb.android.yellowapi.model.Restaurant;

public class SearchRestaurant extends Activity implements OnClickListener {

	// static final String URL
	// ="http://localhost:8080/HalalRestaurantClient/sampleRestaurantWebProxy/input.jsp?method=13";
	RestaurantDataParser res_parser_obj;
	
	String[] new_Array;
	String[] comments=new String[new_Array.length];
	String[] res_Ids=new String[new_Array.length];
	String[] splittedResults=new String[2];
	

	ProgressDialog pDialog;
	NodeList nodelist;
	// ArrayList<Restaurant> fetchedData=new ArrayList<Restaurant>();
	ArrayAdapter restaurants_adapter, states_adapter;
	EventDataSQLHelper eventsData;
	// WebserviceCall services_obj;
	private EditText search_restaurant_name, search_state;
	private Button res_spinner, state_spinner;

	private String[] restaurants_array;
	private String[] states_array;
	private String restaurant;
	private String state;
	private Integer res_id;
	private String[] res_names = {};
	private String[] res_address = {};
	String URL = "http://172.16.6.106:8080/HalalRestaurant/rest/hello";
		String xml = "";

	// ArrayList<Restaurant> parsedWebList=new ArrayList<Restaurant>();
	// ArrayList<Restaurant> updatedRestaurants=new ArrayList<Restaurant>();

	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_search_restaurant);
		res_parser_obj = new RestaurantDataParser(this);

		// StrictMode.ThreadPolicy policy = new
		// StrictMode.ThreadPolicy.Builder().permitAll().build();
		//
		// StrictMode.setThreadPolicy(policy);
		// new DownloadXML().execute(URL);
		// XMLParser parser = new XMLParser();
		// xml = parser.getXmlFromUrl(URL);

		// services_obj=new WebserviceCall();
		eventsData = new EventDataSQLHelper(this);

		res_spinner = (Button) findViewById(R.id.res_spinner);
		state_spinner = (Button) findViewById(R.id.state_spinner);

		// xml=services_obj.getXml("sayHelloWorld");
		res_spinner.setOnClickListener(this);
		state_spinner.setOnClickListener(this);

		// LongRunningGetIO lmib = new LongRunningGetIO();
		//
		// if(lmib.getStatus() == AsyncTask.Status.PENDING){
		// Toast.makeText(this,"pending", Toast.LENGTH_LONG).show();
		// new LongRunningGetIO().cancel(true);
		// // My AsyncTask has not started yet
		// }
		//
		// if(lmib.getStatus() == AsyncTask.Status.RUNNING){
		// Toast.makeText(this,"running", Toast.LENGTH_LONG).show();
		// // My AsyncTask is currently doing work in doInBackground()
		// }
		//
		// if(lmib.getStatus() == AsyncTask.Status.FINISHED){
		// Toast.makeText(this,"finished", Toast.LENGTH_LONG).show();
		// // My AsyncTask is done and onPostExecute was called
		// }

		// if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
		// new
		// DownloadXML().execute(URL).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		// } else {
		// new DownloadXML().execute(URL);
		// }

		// if ((downloadXML != null) && downloadXML.getStatus() ==
		// Status.RUNNING)
		// {
		// // it can be running but cancelled, in that case, return a new
		// instance
		// if (downloadXML.isCancelled())
		// {
		// downloadXML = new DownloadXML();
		// }
		// else
		// {
		// // display a toast to say "try later"
		// Toast.makeText(this, "A task is already running, try later",
		// Toast.LENGTH_SHORT).show();
		//
		// //return null;
		// }
		// }else{
		// if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
		//
		// new
		// DownloadXML().execute(URL).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		// } else {
		// new DownloadXML().execute(URL);
		// }
		// }
		new DownloadXML().execute(URL);

		// Boolean dbvalue=eventsData.checkDB();
		// if(dbvalue==false){
		// // while(xml.length()==0){
		// // new LongRunningGetIO().execute();
		// // }
		// parsedWebList=res_parser_obj.getWebDataList();
		// eventsData.insertWebData(parsedWebList);
		//
		// }
		// else
		// {
		// //get all restaurants from database in arryalist
		// fetchedData=eventsData.getExistingData();
		// parsedWebList=res_parser_obj.getWebDataList();
		// boolean value=compareRecords(parsedWebList, fetchedData);
		// if(value==false){
		//
		// eventsData.update(parsedWebList);
		//
		// }
		// }
		
	    new_Array=eventsData.getRestaurantsReviews();
	    for(int i=0;i<new_Array.length;i++){
	    	splittedResults=new_Array[i].split(",");
	    	res_Ids[i]=splittedResults[1];
	    	comments[i]=splittedResults[2];
	    }

		restaurants_array = eventsData.getRestaurantNames();
		restaurants_adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, restaurants_array);

		states_array = eventsData.getStates();
		states_adapter = new ArrayAdapter(this,
				android.R.layout.simple_spinner_item, states_array);

	}

	// @Override

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.res_spinner:

			new AlertDialog.Builder(v.getContext())
					.setTitle("Select Restaurants")
					.setAdapter(restaurants_adapter,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int selectedRestaurant) {
									// TODO Auto-generated method stub
									restaurant = restaurants_array[selectedRestaurant];
									// Log.e("restaurant",restaurant);

									res_spinner.setText(restaurants_array[selectedRestaurant]);
									res_id = eventsData.getRestaurantID(restaurant);

									Intent intent = new Intent(SearchRestaurant.this,RestaurantActivity.class);
									intent.putExtra("res_id", res_id);

									startActivity(intent);
									dialog.dismiss();
								}
							}).create().show();
			break;

		case R.id.state_spinner:

			new AlertDialog.Builder(v.getContext())
					.setTitle("Select States")
					.setAdapter(states_adapter,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									state = states_array[which];
									state_spinner.setText(states_array[which]);
									res_names = eventsData
											.getRestaurantsForState(state);
									res_address = eventsData
											.getRestaurantAddressForState(state);
									Bundle b = new Bundle();
									b.putStringArray("res_names", res_names);
									b.putStringArray("res_address", res_address);
									Intent intent = new Intent(
											SearchRestaurant.this,
											List_of_Restaurants.class);
									intent.putExtras(b);
									startActivity(intent);
									// Log.e("sates", states);
									dialog.dismiss();
								}
							}).create().show();
			break;
		}
	}

	//
	// public boolean compareRecords(ArrayList<Restaurant>
	// parsedWebList,ArrayList<Restaurant> fetchedData){
	// Restaurant res=new Restaurant();
	// //int webDataSize=parsedWebList.size();
	// int fetchedDataSize=fetchedData.size();
	//
	//
	// for(int i=0;i<parsedWebList.size();i++){
	// Restaurant r=parsedWebList.get(i);
	//
	// if(r.getId().equals(res.getId())){
	// if(r.getName().equals(res.getName())){
	// return true;
	// }
	// else if(r.getAddress().equals(res.getAddress())){
	// return true;
	// }
	// else if(r.getCity().equals(res.getCity())){
	// return true;
	// }
	// else if(r.getState().equals(res.getState())){
	// return true;
	// }
	// else if(r.getPostalCode().equals(res.getPostalCode())){
	// return true;
	// }
	// else if(r.getPhone().equals(res.getPhone())){
	// return true;
	// }
	// else if(r.getLatitude().equals(res.getLatitude())){
	// return true;
	// }
	// else if(r.getLongitude().equals(res.getLongitude())){
	// return true;
	// }
	// }
	//
	//
	//
	// }
	//
	// return false;
	//
	//
	//
	// }
	// private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
	// protected String getASCIIContentFromEntity(HttpEntity entity) throws
	// IllegalStateException, IOException {
	// InputStream in = entity.getContent();
	//
	//
	// StringBuffer out = new StringBuffer();
	// int n = 1;
	// while (n>0) {
	// byte[] b = new byte[4096];
	// n = in.read(b);
	//
	//
	// if (n>0) out.append(new String(b, 0, n));
	// }
	//
	//
	// return out.toString();
	// }
	//
	//
	// @Override
	//
	//
	// protected String doInBackground(Void... params) {
	// HttpClient httpClient = new DefaultHttpClient();
	// HttpContext localContext = new BasicHttpContext();
	// HttpGet httpGet = new
	// HttpGet("http://172.16.16.101:14400/HalalRestaurant/rest/hello");
	// xml = null;
	// try {
	// HttpResponse response = httpClient.execute(httpGet, localContext);
	//
	//
	// HttpEntity entity = response.getEntity();
	//
	//
	// xml = getASCIIContentFromEntity(entity);
	//
	//
	// } catch (Exception e) {
	// Log.e("exception = ", e.toString());
	// return e.getLocalizedMessage();
	// }
	//
	//
	// return xml;
	// }
	//
	//
	// protected void onPostExecute(String results) {
	// if (results!=null) {
	// parsedWebList=res_parser_obj.callParsing(xml);
	//
	// // eventsData.insertWebData(parsedWebList);
	//
	// }
	//
	//
	//
	// }

	public String getXmlFromUrl(String url) {
		String xml = null;

		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return xml;
	}

	
	public class DownloadXML extends AsyncTask<String, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressbar
			pDialog = new ProgressDialog(SearchRestaurant.this);
			// Set progressbar title
			pDialog.setTitle("Android Simple XML Parsing using DOM Tutorial");
			// Set progressbar message
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			if (!pDialog.isShowing()) {
				// Show progressbar
				pDialog.show();
			}
		}

		@Override
		protected Void doInBackground(String... Url) {
			try {
				
				URL url = new URL(Url[0]);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				// Download the XML file
				Document doc = db.parse(new InputSource(url.openStream()));
				doc.getDocumentElement().normalize();
				// Locate the Tag Name
				nodelist = doc.getElementsByTagName("Restaurant");
				res_parser_obj.callParsing(nodelist);

			} catch (Exception e) {
				// Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void args) {
			super.onPostExecute(args);

			// for (int temp = 0; temp < nodelist.getLength(); temp++) {
			// Node nNode = nodelist.item(temp);
			// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			// Element eElement = (Element) nNode;
			// // Set the texts into TextViews from item nodes
			// // Get the title
			// textview.setText(textview.getText() + "Title : "
			// + getNode("title", eElement) + "\n" + "\n");
			// // Get the description
			// textview.setText(textview.getText() + "Description : "
			// + getNode("description", eElement) + "\n" + "\n");
			// // Get the link
			// textview.setText(textview.getText() + "Link : "
			// + getNode("link", eElement) + "\n" + "\n");
			// // Get the date
			// textview.setText(textview.getText() + "Date : "
			// + getNode("date", eElement) + "\n" + "\n" + "\n"
			// + "\n");
			// }
			// }
			// Close progressbar
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
	}

	@Override
	public void onPause() {

		super.onPause();
		if (pDialog != null)
			pDialog.dismiss();
	}

	@Override
	public void onStop() {

		super.onStop();
		if (pDialog != null)
			pDialog.dismiss();
	}
}
