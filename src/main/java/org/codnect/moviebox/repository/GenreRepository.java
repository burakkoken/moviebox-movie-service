package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Burak Köken on 3.1.2020.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select g from Genre g order by g.name asc")
    List<Genre> findAll();

    Optional<Genre> findByName(String name);

}
