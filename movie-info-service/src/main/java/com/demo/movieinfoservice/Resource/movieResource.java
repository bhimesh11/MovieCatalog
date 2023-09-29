package com.demo.movieinfoservice.Resource;

import com.demo.movieinfoservice.model.movie;
import com.demo.movieinfoservice.model.movieResponse;
import com.demo.movieinfoservice.model.moviesummary;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class movieResource
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;



    @GetMapping("/{movieId}")
    public movie getMovieInfo(@PathVariable("movieId") String movieId)
    {
        moviesummary moviedetails = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, moviesummary.class);
        System.out.println(moviedetails.toString());

return new movie(movieId,moviedetails.getTitle(),moviedetails.getOverview());
    }
}
