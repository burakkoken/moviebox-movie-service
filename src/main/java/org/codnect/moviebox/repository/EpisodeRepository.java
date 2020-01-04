package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}
