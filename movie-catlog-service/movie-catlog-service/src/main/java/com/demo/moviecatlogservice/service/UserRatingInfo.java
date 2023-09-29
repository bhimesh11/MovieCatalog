package com.demo.moviecatlogservice.service;

import com.demo.moviecatlogservice.model.Rating;
import com.demo.moviecatlogservice.model.userRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    public userRating getFallBackUserRating(String userId)
    {
        userRating userRating = new userRating();
        userRating.setUserId(userId);
        userRating.setRatings( Arrays.asList(new Rating("0",0)));
        return userRating;
    }

    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
    public userRating getForRatingApi(String userId) {
        return restTemplate.getForObject(
                "http://rating-info-service/ratingsdata/users/" + userId, userRating.class);
    }
}
