package com.jawcruz.consultatweets.rest.model;

import org.bson.types.ObjectId;

public class User {
	//@Id
	//public ObjectId _id;

	public String name;
	public String screen_name;
	public int followers_count;
	public String location;
	public String language;

	public User() {
	}

	public User(ObjectId _id, String name, String screen_name, int followers_count, String location, String language) {
		super();
		//this._id = _id;
		this.name = name;
		this.screen_name = screen_name;
		this.followers_count = followers_count;
		this.location = location;
		this.language = language;
	}
/*
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}
*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public int getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
}