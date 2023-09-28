package com.demo.moviecatlogservice.Resource;


import com.demo.moviecatlogservice.model.Rating;
import com.demo.moviecatlogservice.model.catalogItem;
import com.demo.moviecatlogservice.model.movie;
import com.demo.moviecatlogservice.model.userRating;
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

    @GetMapping("/{userId}")
    private List<catalogItem> getCatalog(@PathVariable("userId") String userId) {
        userRating ratings = restTemplate.getForObject(
                "http://rating-info-service/ratingsdata/users/" + userId, userRating.class);

        return ratings.getRatings().stream().
                map(rate ->
                {
                    movie movies =
                            restTemplate.getForObject("http://movie-info-service/movies/" + rate.getRating(), movie.class);
                    return new catalogItem(movies.getName(), "description", rate.getRating());
                }).collect(Collectors.toList());

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




}
