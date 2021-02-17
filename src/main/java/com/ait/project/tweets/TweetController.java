package com.ait.project.tweets;

import com.ait.project.exceptions.TweetExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tweets")
public class TweetController {

    private static final String CONSUMER_KEY = "QKewAgdZH3fjbZ9Tq2kZtWuKY";
    private static final String CONSUMER_SECRET = "5fvnQPS2NWAneFKGVH56bo66Ls7YN6mIevSHu9EyRwaeTtGaQw";
    private static final String ACCESS_TOKEN = "1351855182745907207-zffQPS2vgscmqRg1KJSA7yJ4MGuoMc";
    private static final String ACCESS_TOKEN_SECRET = "rs6cQlfizz3rtbMlLBFrAIpkCeUJTEWJz6kIjcQRFk7F7";

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<Tweet> getTweetsInDatabase() {
        return tweetService.getTweets();
    }

    @PostMapping
    public void addLatestTweets() {

        List<Tweet> tweets = new ArrayList<Tweet>();

        try {
            List<Status> statusTweets = searchTweets("covid");

            for(Status  status : statusTweets) {

                Tweet tweet = new Tweet(status.getText(), status.getFavoriteCount(), status.getRetweetCount(), status.getUser().getFollowersCount(), status.getCreatedAt(), status.getUser().getLocation());
                if (tweet.getText().length() < 255) {
                    tweets.add(tweet);
                }
            }
        } catch (TwitterException e) {
            System.out.println(e.getErrorMessage());
        }

        for(Tweet tweet: tweets) {
            try {
                tweetService.addNewTweet(tweet);
            } catch (TweetExistsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Status> searchTweets(String keyword) throws TwitterException {

        List<Status> tweets = Collections.emptyList();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true).setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
        query.setCount(100);
        query.setLocale("en");
        query.setLang("en");

        QueryResult queryResult = twitter.search(query);
        tweets = queryResult.getTweets();

        return tweets;
    }

}