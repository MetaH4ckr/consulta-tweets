package com.jawcruz.consultatweets.rest.model;

import org.springframework.data.annotation.Id;

public class TweetsByHour {

	@Id
	private int hour;
	private int count;

	public TweetsByHour() {
	}

	public TweetsByHour(int hour, int count) {
		super();
		this.hour = hour;
		this.count = count;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}