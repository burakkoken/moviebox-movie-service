package org.codnect.moviebox.exception;

public class GenreDuplicateException extends DuplicateException {

    public GenreDuplicateException(String genreName) {
        super("Genre already exists with genre name called " + genreName);
    }

}
