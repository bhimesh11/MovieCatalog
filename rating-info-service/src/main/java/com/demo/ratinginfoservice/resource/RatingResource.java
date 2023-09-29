package com.demo.ratinginfoservice.resource;

import com.demo.ratinginfoservice.model.Rating;
import com.demo.ratinginfoservice.model.userRating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    List<Rating> rating = Arrays.asList(
            new Rating("1234",4),
            new Rating("5678",3),
            new Rating("foo",4),
            new Rating("transformers",5),
            new Rating("151368",5));

    @GetMapping("/{movieId}")
    public ResponseEntity<Rating> getRating(@PathVariable("movieId")  String movieId)
    {
        List<Rating> rating = Arrays.asList(
                new Rating("1234",4),
                new Rating("5678",3),
                new Rating("foo",4),
                new Rating("transformers",5),
                new Rating("151368",5));

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
    public userRating getUserRating(@PathVariable("userId") String movieId) {

        boolean isMovieIdExists = rating.stream().anyMatch(frate -> frate.getMovieId().equals(movieId));

        List<Rating> updatedRating = null;
        if (!isMovieIdExists) {
            updatedRating = addRating(movieId);
        }
        System.out.println(updatedRating);
        Optional<Rating> matched = updatedRating.stream().filter(rate -> rate.getMovieId().equalsIgnoreCase(movieId)).findFirst();

        System.out.println(matched.toString());

        userRating userrating = new userRating();
        if (matched.isPresent()) {
            Rating ratingMatch = matched.get();
            System.out.println(ratingMatch.toString());
            userrating.setRatings(Collections.singletonList(ratingMatch));
        }

        userrating.setUserId(movieId);
        return userrating;

        // return rating;
    }

    private List<Rating> addRating(String movieId) {


    Random random = new Random();
        int[] ratingarray = {3,4,5};
        int randomrating =  ratingarray[random.nextInt(ratingarray.length)];
        Rating ratingnew  = new Rating(movieId,randomrating);
        List<Rating> updatedList = new ArrayList<>(rating);
        updatedList.add(ratingnew);

        return updatedList;
    }
}
