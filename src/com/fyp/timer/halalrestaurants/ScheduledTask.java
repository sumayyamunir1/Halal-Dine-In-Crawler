package com.fyp.timer.halalrestaurants;

import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.fyp.halalrestaurants.SearchRestaurant;

import com.fyp.halalrestaurants.SearchRestaurant.DownloadXML;

public class ScheduledTask extends TimerTask{
	SearchRestaurant search_obj;
	String URL="";
public void run() {
	search_obj.new DownloadXML().execute(URL);
	}

}
