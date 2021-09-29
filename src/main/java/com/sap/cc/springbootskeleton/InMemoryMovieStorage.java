package com.sap.cc.springbootskeleton;

import java.util.*;


public class InMemoryMovieStorage {

    private final Map<Long, Movie> movies = new HashMap<>();

    public InMemoryMovieStorage() {
    }

    public Movie save(final Movie movie) {
        boolean isBookIdEmptyOrNonExisting = movie.getId() == null || !movies.containsKey(movie.getId());

        if (isBookIdEmptyOrNonExisting) {
            Long id = movies.size() + 1L;
            movie.setId(id);
        }

        movies.put(movie.getId(), movie);
        return movie;
    }

    public Optional<Movie> get(Long id) {
        return Optional.ofNullable(movies.get(id));
    }

    public List<Movie> getAll() {
        return new ArrayList<>(movies.values());
    }

    public void deleteAll() {
        movies.clear();
    }

    public void delete(Long id) {
        movies.remove(id);
    }
}