package com.sap.cc.movies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MovieFinderTest {

    MovieFinder movieFinder = new MovieFinder();
    InMemoryMovieStorage movieStorage = new InMemoryMovieStorage();

    List<Movie> movies = Arrays.asList(
            new Movie("The Godfather", "Francis Ford Coppola"),
            new Movie("The Godfather: Part 2", "Francis Ford Coppola"),
            new Movie("The Thing", "John Carpenter"));

    @BeforeEach
    void setUp() {
        movies.forEach(movieStorage::save);
    }

    @Test
    void getAllMoviesShouldReturnAllMovies() {
        List<Movie> allMovies = movieFinder.getAllMovies();

        assertThat(allMovies).hasSameSizeAs(movies);
    }

    @Test
    void findMoviesByDirector_calledWithFrancis_shouldReturnTheGodfatherMovies() {
        List<Movie> francisMovies = movieFinder.findMoviesByDirector("Francis Ford Coppola");

        assertThat(francisMovies).hasSize(2);
        for(Movie movie: francisMovies){
            assertThat(movie.getDirector()).isEqualTo("Francis Ford Coppola");
        }
    }

    @Test
    void findMoviesByDirector_calledWithFrancisAllLowerCase_shouldReturnTheGodfatherMovies() {
        List<Movie> francisMovies = movieFinder.findMoviesByDirector("francis ford coppola");

        assertThat(francisMovies).hasSize(2);
        for(Movie movie: francisMovies){
            assertThat(movie.getDirector()).isEqualTo("Francis Ford Coppola");
        }
    }

    @Test
    void findMoviesByTitle_calledWithThe_ReturnsEveryMovieContainingThe() {
        List<Movie> moviesWithThe = movieFinder.findMoviesByTitle("The");

        assertThat(moviesWithThe).hasSameSizeAs(movies);
    }

    @Test
    void findMoviesByTitle_calledWithGodfather_ReturnsGodfatherMovies() {
        List<Movie> moviesWithGodfather = movieFinder.findMoviesByTitle("Godfather");

        assertThat(moviesWithGodfather).hasSize(2);
        for (Movie movie: moviesWithGodfather){
            assertThat(movie.getTitle().toUpperCase()).contains("Godfather".toUpperCase());
        }
    }
}