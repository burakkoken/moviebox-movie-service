package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.TvSeries;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@Repository
public interface TvSeriesRepository extends JpaRepository<TvSeries, Long> {

    @Query("select t from TvSeries t left join fetch t.genres")
    List<TvSeries> findAllTvSeries(Pageable pageable);

    @Query("select t from TvSeries t left join fetch t.genres g where g.id = ?1")
    List<TvSeries> findTvSeriesByGenre(Pageable pageable, Long genreId);

}
