package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {

}
