package com.practice.moviecatlogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.moviecatlogservice.resource.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class RatingInfo {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getfallbackUserRatings")
    public List<Rating> getUserRatings(String userId)
    {
        Rating rating=restTemplate.getForObject("http://movie-rating-service/ratings/"+userId,Rating.class);
        return Arrays.asList(rating);
    }



    public List<Rating> getfallbackUserRatings(String userId)
    {
        Rating rating= new Rating();
        rating.setUserId(userId);
        rating.setRating(0);
        rating.setMovieId("not known");
        return  Arrays.asList(rating);

    }
}
