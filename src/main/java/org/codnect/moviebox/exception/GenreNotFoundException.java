package org.codnect.moviebox.exception;

public class GenreNotFoundException extends NotFoundException {

    public GenreNotFoundException(Long genreId) {
        super("Genre not found with genre id : " + genreId);
    }

}
