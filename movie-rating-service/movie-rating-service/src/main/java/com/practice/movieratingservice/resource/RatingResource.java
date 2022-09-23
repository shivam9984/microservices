package com.practice.movieratingservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//Controoler class
@RestController
@RequestMapping("/ratings")
public class RatingResource {
    RestTemplate restTemplate= new RestTemplate();


    @GetMapping("/{userId}")
    public Rating getRatingById(@PathVariable("userId") String userId)
    {

       return new Rating("mv1",4);



    }


}
