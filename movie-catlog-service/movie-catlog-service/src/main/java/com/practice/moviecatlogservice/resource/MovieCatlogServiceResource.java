package com.practice.moviecatlogservice.resource;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.practice.moviecatlogservice.services.MovieInfo;
import com.practice.moviecatlogservice.services.RatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// to make call by web use Rest Controller
@RestController
@RequestMapping("/catlog")
public class MovieCatlogServiceResource {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MovieInfo movieInfo;
    @Autowired
   private RatingInfo ratingInfo;
    // creating the class
    @RequestMapping("/{userId}")
//    @HystrixCommand(fallbackMethod = "getFallbackcatlog")
    public List<CatlogItem> getDescriptionById(@PathVariable("userId") String userId)
    {

        List<Rating> rating= ratingInfo.getUserRatings(userId);

     return rating.stream().map(rating1 -> {
         // getting the ratings by movieId

          // for each rating call the movie rest api using restTempplate
         return movieInfo.getCatlogItem(rating1);
      }).collect(Collectors.toList());


    }





}
