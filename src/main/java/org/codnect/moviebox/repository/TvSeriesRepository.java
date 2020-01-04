package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.TvSeries;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@RestController
public interface TvSeriesRepository extends JpaRepository<TvSeries, Long> {

    @Query("select t from TvSeries t left join fetch t.genres")
    Set<TvSeries> findAllTvSeries(Pageable pageable);

    @Query("select t from TvSeries T left join fetch t.genres g where g.id = ?1")
    Set<TvSeries> findTvSeriesByGenre(Pageable pageable, Long genreId);

}
