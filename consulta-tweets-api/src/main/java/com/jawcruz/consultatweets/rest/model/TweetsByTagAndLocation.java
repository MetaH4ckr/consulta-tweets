package com.jawcruz.consultatweets.rest.model;

public class TweetsByTagAndLocation {

	private String searchedtag;
	private String location;
	private String lang;
	private int count;

	public TweetsByTagAndLocation() {
	}

	public TweetsByTagAndLocation(String searchedtag, String location, String lang, int count) {
		super();
		this.searchedtag = searchedtag;
		this.location = location;
		this.lang = lang;
		this.count = count;
	}

	public String getSearchedtag() {
		return searchedtag;
	}

	public void setSearchedtag(String searchedtag) {
		this.searchedtag = searchedtag;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}