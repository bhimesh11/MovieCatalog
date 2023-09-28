package com.demo.movieinfoservice.Resource;

import com.demo.movieinfoservice.model.movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class movieResource
{

    @GetMapping("/{movieId}")
    public movie getMovieInfo(@PathVariable("movieId") String movieId)
    {
return new movie("Transformers",movieId);
    }
}
