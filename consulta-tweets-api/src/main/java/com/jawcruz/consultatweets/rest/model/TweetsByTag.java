package com.jawcruz.consultatweets.rest.model;

import org.springframework.data.annotation.Id;

public class TweetsByTag {

	@Id
	private String searchedtag;
	private int count;

	public TweetsByTag() {
	}

	public TweetsByTag(String searchedtag, int count) {
		super();
		this.searchedtag = searchedtag;
		this.count = count;
	}

	public String getSearchedtag() {
		return searchedtag;
	}

	public void setSearchedtag(String searchedtag) {
		this.searchedtag = searchedtag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}