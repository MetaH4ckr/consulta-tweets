package com.jawcruz.consultatweets.rest.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.jawcruz.consultatweets.rest.model.Tweets;

public interface TweetRepository extends MongoRepository<Tweets, String> {
	Tweets findBy_id(ObjectId _id);
	
	List<Tweets> findTop10BySearchedtag(String searchedtag);
}