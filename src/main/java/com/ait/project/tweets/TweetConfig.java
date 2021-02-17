package com.ait.project.tweets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TweetConfig {

    @Bean
    CommandLineRunner commandLineRunner(TweetRepository repository) {
        return args -> { };
    }
}