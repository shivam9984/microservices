package com.practice.moviecatlogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.moviecatlogservice.resource.CatlogItem;
import com.practice.moviecatlogservice.resource.Movie;
import com.practice.moviecatlogservice.resource.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "getfallbackCatlogItem")
    public CatlogItem getCatlogItem(Rating rating)
    {
        Movie movie= restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
        return new CatlogItem(movie.getName(),"desc", rating.getRating());
    }

    public CatlogItem getfallbackCatlogItem(Rating rating)
    {
        return new CatlogItem("movie name not found", " " , rating.getRating());

    }
}
