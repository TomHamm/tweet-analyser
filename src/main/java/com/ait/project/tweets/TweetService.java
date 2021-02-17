package com.ait.project.tweets;

import com.ait.project.exceptions.TweetExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<Tweet> getTweets() {
        return tweetRepository.findAll();
    }

    public void addNewTweet(Tweet tweet) throws TweetExistsException {
        Optional<Tweet> tweetOptional = tweetRepository
                .findTweetByText(tweet.getText());
        if (tweetOptional.isPresent()) {
            throw new TweetExistsException("\n Not adding Tweet: \'" + tweet.getText() + " \' - Tweet already exists in the Database\n");
        }
        tweetRepository.save(tweet);
    }
}