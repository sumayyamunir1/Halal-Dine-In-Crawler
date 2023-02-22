package com.fyp.halalrestaurants;

public class WebData {
	public int RestaurantID;
	public String RestaurantName;
	public String RestaurantAddress;
	public String RestaurantCity;
	public String RestaurantState;
	public String RestaurantpostalCode;
	public int RestaurantPhone;
	public Double RestaurantLatitude;
	public Double RestaurantLongitude;

	public WebData() {

	}

	public WebData(int RestaurantID, String RestaurantName,
			String RestaurantAddress, String RestaurantCity,
			String RestaurantState, String RestaurantpostalCode,
			int RestaurantPhone, Double RestaurantLatitude,
			Double RestaurantLongitude) {
		this.RestaurantID = RestaurantID;
		this.RestaurantName = RestaurantName;
		this.RestaurantAddress = RestaurantAddress;
		this.RestaurantCity = RestaurantCity;
		this.RestaurantState = RestaurantState;
		this.RestaurantpostalCode = RestaurantpostalCode;
		this.RestaurantPhone = RestaurantPhone;
		this.RestaurantLatitude = RestaurantLatitude;
		this.RestaurantLongitude = RestaurantLongitude;
	}

	public int getRestaurantID() {
		return RestaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		RestaurantID = restaurantID;
	}

	public String getRestaurantName() {
		return RestaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		RestaurantName = restaurantName;
	}

	public String getRestaurantAddress() {
		return RestaurantAddress;
	}

	public void setRestaurantAddress(String restaurantAddress) {
		RestaurantAddress = restaurantAddress;
	}

	public String getRestaurantCity() {
		return RestaurantCity;
	}

	public void setRestaurantCity(String restaurantCity) {
		RestaurantCity = restaurantCity;
	}

	public String getRestaurantState() {
		return RestaurantState;
	}

	public void setRestaurantState(String restaurantState) {
		RestaurantState = restaurantState;
	}

	public String getRestaurantpostalCode() {
		return RestaurantpostalCode;
	}

	public void setRestaurantpostalCode(String restaurantpostalCode) {
		RestaurantpostalCode = restaurantpostalCode;
	}

	public int getRestaurantPhone() {
		return RestaurantPhone;
	}

	public void setRestaurantPhone(int restaurantPhone) {
		RestaurantPhone = restaurantPhone;
	}

	public Double getRestaurantLatitude() {
		return RestaurantLatitude;
	}

	public void setRestaurantLatitude(Double restaurantLatitude) {
		RestaurantLatitude = restaurantLatitude;
	}

	public Double getRestaurantLongitude() {
		return RestaurantLongitude;
	}

	public void setRestaurantLongitude(Double restaurantLongitude) {
		RestaurantLongitude = restaurantLongitude;
	}

}
