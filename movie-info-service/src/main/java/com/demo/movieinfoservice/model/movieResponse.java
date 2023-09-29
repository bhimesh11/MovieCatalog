package com.demo.movieinfoservice.model;

public class movieResponse
{
    private movie movie;

    public movieResponse(com.demo.movieinfoservice.model.movie movie) {
        this.movie = movie;
    }

    public com.demo.movieinfoservice.model.movie getMovie() {
        return movie;
    }


    public void setMovie(com.demo.movieinfoservice.model.movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "movieResponse{" +
                "movie=" + movie +
                '}';
    }
}
