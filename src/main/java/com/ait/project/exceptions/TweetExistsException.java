package com.ait.project.exceptions;

public class TweetExistsException extends Exception {
    public TweetExistsException(String message){
        super(message);
    }
}