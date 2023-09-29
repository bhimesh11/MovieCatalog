package com.demo.moviecatlogservice.service;

import com.demo.moviecatlogservice.model.Rating;
import com.demo.moviecatlogservice.model.catalogItem;
import com.demo.moviecatlogservice.model.movie;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallBackmovieCatalog")
    public catalogItem getCatalogItemforMovies(Rating rate) {
        movie movies =
                restTemplate.getForObject("http://movie-info-service/movies/" + rate.getRating(), movie.class);
        return new catalogItem(movies.getName(), "description", rate.getRating());
    }
    public catalogItem getFallBackmovieCatalog(Rating rate)
    {

        return new catalogItem("movie name not found","",rate.getRating());
    }
}
