package com.jawcruz.consultatweets.rest.model;

import org.springframework.data.annotation.Id;

public class TopUser {
	@Id
	private String screen_name;
	private int followers_count;

	public TopUser() {
	}

	public TopUser(String screen_name, int followers_count) {
		super();
		this.screen_name = screen_name;
		this.followers_count = followers_count;
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

}