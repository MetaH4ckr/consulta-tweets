package com.jawcruz.consultatweets.rest.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Tweets {
	@Id
	private ObjectId _id;

	private String searchedtag;
	private String text;
	private Date created_at_dt;
	private User user;

	public Tweets() {
	}

	public Tweets(ObjectId _id, String searchedtag, String text, Date created_at_dt, User user) {
		super();
		this._id = _id;
		this.searchedtag = searchedtag;
		this.text = text;
		this.created_at_dt = created_at_dt;
		this.user = user;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getSearchedtag() {
		return searchedtag;
	}

	public void setSearchedtag(String searchedtag) {
		this.searchedtag = searchedtag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated_at_dt() {
		return created_at_dt;
	}

	public void setCreated_at_dt(Date created_at_dt) {
		this.created_at_dt = created_at_dt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}