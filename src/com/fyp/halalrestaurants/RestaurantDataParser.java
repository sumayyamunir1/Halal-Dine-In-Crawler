package com.fyp.halalrestaurants;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.content.Context;

import com.ccvb.android.yellowapi.model.Restaurant;

public class RestaurantDataParser {

	SearchRestaurant search_res_obj;
	EventDataSQLHelper eventsData;
	// ArrayList<Restaurant> parsedWebList=new ArrayList<Restaurant>();
	// RestaurantDataParser res_parser_obj=new RestaurantDataParser();
	// ArrayList<Restaurant> fetchedData=new ArrayList<Restaurant>();
	static final String KEY_RESTAURANT = "Restaurant"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_NAME = "name";
	static final String KEY_ADDRESS = "address";
	static final String KEY_CITY = "city";
	static final String KEY_STATE = "state";
	static final String KEY_POSTALCODE = "postalcode";
	static final String KEY_PHONE = "phone";
	static final String KEY_LATITUDE = "latitude";
	static final String KEY_LONGITUDE = "longitude";

	private String xml;
	ArrayList<Restaurant> webDataList = new ArrayList<Restaurant>();
	Context context;

	// ArrayList<Restaurant>

	// public ArrayList<Restaurant> getWebDataList() {
	// return webDataList;
	// }
	//
	// public void setWebDataList(ArrayList<Restaurant> webDataList) {
	// this.webDataList = webDataList;
	// }

	public RestaurantDataParser(Context searchRestaurant) {
		context = searchRestaurant;
		eventsData = new EventDataSQLHelper(context);
		// TODO Auto-generated constructor stub
	}

	public void callParsing(NodeList xml) {
		Restaurant res = null;

		XMLParser parser = new XMLParser();

		// Document doc = parser.getDomElement(xml); // getting DOM element
		NodeList nl = xml;
		// NodeList nl = doc.getElementsByTagName(KEY_RESTAURANT);

		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			res = new Restaurant();
			res.setId(parser.getValue(e, KEY_ID));
			res.setName(parser.getValue(e, KEY_NAME));
			res.setAddress(parser.getValue(e, KEY_ADDRESS));
			res.setCity(parser.getValue(e, KEY_CITY));
			res.setState(parser.getValue(e, KEY_STATE));
			res.setPostalCode(parser.getValue(e, KEY_POSTALCODE));
			res.setPhone(parser.getValue(e, KEY_PHONE));
			res.setLatitude(parser.getValue(e, KEY_LATITUDE));
			res.setLongitude(parser.getValue(e, KEY_LONGITUDE));

			webDataList.add(i, res);
		}
		// setWebDataList(webDataList);
		// return webDataList;

		// Boolean dbvalue=eventsData.checkDB();
		// if(dbvalue==false){

		// parsedWebList=getWebDataList();
		eventsData.update(webDataList, eventsData);

		// }
		// else{
		// get all restaurants from database in arraylist
		// fetchedData=eventsData.getExistingData();
		// parsedWebList=getWebDataList();
		//
		// boolean value=search_res_obj.compareRecords(parsedWebList,
		// fetchedData);
		// if(value==false){
		//
		// eventsData.update(parsedWebList);
		//
		// }
		// }
		// public ArrayList<Restaurant> getParsedData(ArrayList<Restaurant>
		// list){
		// return list;
		//
		// }

	}
}
