package com.jawcruz.consultatweets.rest;

//imports as static
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jawcruz.consultatweets.rest.model.TopUser;
import com.jawcruz.consultatweets.rest.model.Tweets;
import com.jawcruz.consultatweets.rest.model.TweetsByHour;
import com.jawcruz.consultatweets.rest.model.TweetsByTag;
import com.jawcruz.consultatweets.rest.model.TweetsByTagAndLocation;
import com.jawcruz.consultatweets.rest.repository.TweetRepository;

@RestController
public class TweetController {
	@Autowired
	private MongoTemplate template;
	@Autowired
	private TweetRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome() {
		return "Consulta Tweets is online!";
	}

	@RequestMapping(value = "/tweetCountByTag", method = RequestMethod.GET)
	public List<TweetsByTag> getTweetCountByTag(@RequestParam(required = false) String tag) {
		Aggregation aggregation;
		GroupOperation groupBySearchedtag = group("searchedtag").count().as("count");
		SortOperation sortByCountDesc = sort(Sort.by("count").descending());

		if (tag != null) {
			MatchOperation filterBySearchedtag = match(new Criteria("searchedtag").is(tag));
			aggregation = newAggregation(filterBySearchedtag, groupBySearchedtag, sortByCountDesc);
		} else
			aggregation = newAggregation(groupBySearchedtag, sortByCountDesc);

		AggregationResults<TweetsByTag> result = template.aggregate(aggregation, "tweets", TweetsByTag.class);
		return result.getMappedResults();
	}

	@RequestMapping(value = "/top10TweetsByTag", method = RequestMethod.GET)
	public List<Tweets> getTop10TweetsByTag(@RequestParam String tag) {
		return repository.findTop10BySearchedtag(tag);
	}

	@RequestMapping(value = "/top5Users", method = RequestMethod.GET)
	public List<TopUser> getTop5Users() {

		GroupOperation groupByUserName = group("user.screen_name").max("user.followers_count").as("followers_count");
		SortOperation sortByFollowerCountDesc = sort(Sort.by("followers_count").descending());
		LimitOperation limitTop5UserName = limit(5);
		Aggregation aggregation = newAggregation(groupByUserName, sortByFollowerCountDesc, limitTop5UserName);

		AggregationResults<TopUser> result = template.aggregate(aggregation, "tweets", TopUser.class);
		return result.getMappedResults();

	}

	@RequestMapping(value = "/tweetCountByTagAndLocation", method = RequestMethod.GET)
	public List<TweetsByTagAndLocation> getTweetCountByTagAndCountry(@RequestParam(required = false) String tag) {
		Aggregation aggregation;
		GroupOperation groupByTagAndLocation = group("searchedtag", "user.location", "user.lang").count().as("count");
		SortOperation sortByCountDesc = sort(Sort.by("count").descending());

		if (tag != null) {
			MatchOperation filterBySearchedtag = match(new Criteria("searchedtag").is(tag));
			aggregation = newAggregation(filterBySearchedtag, groupByTagAndLocation, sortByCountDesc);
		} else
			aggregation = newAggregation(groupByTagAndLocation, sortByCountDesc);

		AggregationResults<TweetsByTagAndLocation> result = template.aggregate(aggregation, "tweets",
				TweetsByTagAndLocation.class);
		return result.getMappedResults();
	}

	@RequestMapping(value = "/totalTweetsByHour", method = RequestMethod.GET)
	public List<TweetsByHour> getTotalTweetsByHour() {
		Aggregation aggregation;
		ProjectionOperation projectHour = project("created_at_dt").andExpression("hour(created_at_dt)").as("hour");
		GroupOperation gropByHour = group("hour").count().as("count");
		SortOperation sortByHour = sort(Sort.by("hour").ascending());

		aggregation = newAggregation(projectHour, gropByHour, sortByHour);

		AggregationResults<TweetsByHour> result = template.aggregate(aggregation, "tweets", TweetsByHour.class);

		return result.getMappedResults();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Tweets createSimpleTweet(@Valid @RequestBody Tweets simpleTweet) {
		simpleTweet.set_id(ObjectId.get());
		repository.save(simpleTweet);
		return simpleTweet;
	}
}