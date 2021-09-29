package com.sap.cc.movies;

import java.util.List;
import java.util.stream.Collectors;

public class MovieFinder {

    InMemoryMovieStorage movieStorage;

    public MovieFinder() {
        this.movieStorage = new InMemoryMovieStorage();
    }

    public List<Movie> findMoviesByDirector(String director) {
        return movieStorage.getAll().stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }

    public List<Movie> findMoviesByTitle(String title) {
        return movieStorage.getAll().stream()
                .filter(movie -> movie.getTitle().toUpperCase().contains(title.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return movieStorage.getAll();
    }

    public InMemoryMovieStorage getMovieStorage() {
        return movieStorage;
    }


}