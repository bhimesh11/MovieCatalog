package com.demo.moviecatlogservice.Resource;


import com.demo.moviecatlogservice.model.Rating;
import com.demo.moviecatlogservice.model.catalogItem;
import com.demo.moviecatlogservice.model.movie;
import com.demo.moviecatlogservice.model.userRating;
import com.demo.moviecatlogservice.service.MovieInfo;
import com.demo.moviecatlogservice.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class movieCatalogResouce {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userId}")
    private List<catalogItem> getCatalog(@PathVariable("userId") String userId) {

        userRating ratings = userRatingInfo.getForRatingApi(userId);

        return ratings.getRatings().stream().
                map(rate ->
                     movieInfo.getCatalogItemforMovies(rate))
                .collect(Collectors.toList());

    }




    private List<catalogItem> getFallBackCatalog(@PathVariable("userId") String userId) {

        return Arrays.asList(new catalogItem("No Movie","", 0));
    }


}
//        return Collections.singletonList(
//                new catalogItem("Transformer",
//                        "Test",
//                        4));

    //    movie movie  =   webClientBuilder.build()
//                   .get()
//                   .uri("http://movie-info-service/movies/" + Rating.getMovieId())
//                   .retrieve()
//                   .bodyToMono(movie.class)
//                   .block();
        //put them all together
        //  RestTemplate restTemplate = new RestTemplate();
        //  movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + , movie.class);
//        webClientBuilder.build()
//                .get()
//                .uri()
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234",4),
//                new Rating("5678",3));
        // movie movie =  restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId() , movie.class);




