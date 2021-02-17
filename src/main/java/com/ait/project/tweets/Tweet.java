package com.ait.project.tweets;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Tweet {

    @Id
    @SequenceGenerator(
            name = "tweet_sequence",
            sequenceName = "tweet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tweet_sequence"
    )

    private long id;
    private int retweetCount, likedCount, followersCount;
    private String text, location;
    private Date timeTweeted;

    public Tweet() {}

    public Tweet(String text, int retweetCount, int likedCount, int followersCount, Date timeTweeted, String location) {
        this.text = text;
        this.retweetCount = retweetCount;
        this.likedCount = likedCount;
        this.followersCount = followersCount;
        this.timeTweeted = timeTweeted;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public Date getTimeTweeted() {
        return timeTweeted;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", text='" + text +
                ", retweetCount='" + retweetCount +
                ", likedCount='" + likedCount +
                ", followersCount='" + followersCount +
                ", timeTweeted='" + timeTweeted +
                ", location='" + location +
                '}';
    }
}