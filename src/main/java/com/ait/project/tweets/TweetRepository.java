package com.ait.project.tweets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TweetRepository
        extends JpaRepository<Tweet, Long> {

    Optional<Tweet> findTweetByText(String text);
}