package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Burak Köken on 3.1.2020.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m left join fetch m.genres")
    Set<Movie> findAllMovies(Pageable pageable);

    @Query("select m from Movie m left join fetch m.genres g where g.id = ?1")
    Set<Movie> findMoviesByGenre(Pageable pageable, Long genreId);

    @Query("select m from Movie m left join fetch m.genres g where m.imdb <= ?1 and m.imdb >= ?2")
    Set<Movie> findMoviesByImdbLessThanEqualAndImdbGreaterThanEqual(Pageable pageable, Double imdbLess, Double imdbGreater);

}
