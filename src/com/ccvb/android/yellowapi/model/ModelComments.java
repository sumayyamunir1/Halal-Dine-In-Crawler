package com.ccvb.android.yellowapi.model;

public class ModelComments {

	public void setUserComment(String usercomment) {
		this.usercomment=usercomment;
		
	}

	public void setUserName(String username) {
		this.username=username;
		
	}
	public String getUserComment() {
		return usercomment;
	}
	public String getUserName() {
		return username;
	}
	private String username;
	private String usercomment;

}
