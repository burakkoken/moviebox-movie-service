package org.codnect.moviebox.exception;

public class MovieNotFoundException extends NotFoundException {

    public MovieNotFoundException(Long movieId) {
        super("Movie not found with movie id : " + movieId);
    }

}
