package com.demo.ratinginfoservice.resource;

import com.demo.ratinginfoservice.model.Rating;
import com.demo.ratinginfoservice.model.userRating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @GetMapping("/{movieId}")
    public ResponseEntity<Rating> getRating(@PathVariable("movieId")  String movieId)
    {
        List<Rating> rating = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3),
                new Rating("foo",4),
        new Rating("transformers",5)
        );

        Optional<Rating> matching = rating.stream().filter(Rating -> Rating.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();

        if(matching.isPresent())
        {
            return  ResponseEntity.ok(matching.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("users/{userId}")
    public userRating getUserRating(@PathVariable("userId") String movieId)
    {
        List<Rating> rating = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3),
                new Rating("foo",4),
                new Rating("transformers",5)
        );

        userRating userrating = new userRating();
        userrating.setRatings(rating);
        userrating.setUserId(movieId);
        return userrating;

       // return rating;
    }
}
