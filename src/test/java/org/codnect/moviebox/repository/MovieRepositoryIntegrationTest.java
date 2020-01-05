package org.codnect.moviebox.repository;

import org.codnect.moviebox.model.Genre;
import org.codnect.moviebox.model.Movie;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MovieRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void init() {
        /* science-fiction */
        Genre genreScienceFiction = new Genre();
        genreScienceFiction.setName("Science-Fiction");
        entityManager.persist(genreScienceFiction);
        entityManager.flush();
        /* action */
        Genre genreAction = new Genre();
        genreAction.setName("Action");
        entityManager.persist(genreAction);
        entityManager.flush();
        /* horror */
        Genre genreHorror = new Genre();
        genreHorror.setName("Horror");
        entityManager.persist(genreHorror);
        entityManager.flush();
        /* drama */
        Genre genreDrama = new Genre();
        genreDrama.setName("Drama");
        entityManager.persist(genreDrama);
        entityManager.flush();

        /* movie */
        Movie movieCaptainAmerica = new Movie();
        movieCaptainAmerica.setTitle("Captain America");
        movieCaptainAmerica.setImdb(7.4);
        movieCaptainAmerica.setOverview("Captain America Overview");
        movieCaptainAmerica.setRuntime(180);
        movieCaptainAmerica.setReleaseDate(Calendar.getInstance().getTime());
        movieCaptainAmerica.addGenre(genreScienceFiction);
        movieCaptainAmerica.addGenre(genreAction);
        entityManager.persist(movieCaptainAmerica);

        Movie movieFastAndFurious = new Movie();
        movieFastAndFurious.setTitle("Fast & Furious");
        movieFastAndFurious.setImdb(8.9);
        movieFastAndFurious.setOverview("Fast & Furious Overview");
        movieFastAndFurious.setRuntime(175);
        movieFastAndFurious.setReleaseDate(Calendar.getInstance().getTime());
        movieFastAndFurious.addGenre(genreAction);
        movieFastAndFurious.addGenre(genreDrama);
        entityManager.persist(movieFastAndFurious);

        Movie movieFastAndFurious2 = new Movie();
        movieFastAndFurious2.setTitle("Fast & Furious 2");
        movieFastAndFurious2.setImdb(9.0);
        movieFastAndFurious2.setOverview("Fast & Furious 2 Overview");
        movieFastAndFurious2.setRuntime(195);
        movieFastAndFurious2.setReleaseDate(Calendar.getInstance().getTime());
        movieFastAndFurious2.addGenre(genreAction);
        movieFastAndFurious2.addGenre(genreDrama);
        entityManager.persist(movieFastAndFurious2);

        /* flush */
        entityManager.flush();
    }

    @Test
    @DisplayName("movieRepository.findAllMovies(null)")
    @Order(value = 0)
    public void whenFindAllMoviesWithoutPageable_thenReturnMovies() {
        assertThat(movieRepository.findAllMovies(null))
                .hasSize(3);
    }

    @Test
    @DisplayName("movieRepository.findAll(Pageable)")
    public void whenFindAllMoviesWithPageable_thenReturnMovies() {
        assertThat(movieRepository.findAllMovies(PageRequest.of(0, 2)))
                .hasSize(2);
    }

    @Test
    @DisplayName("movieRepository.findMoviesByGenre(null, 1#Science-Fiction)")
    public void whenFindMoviesByGenreWithoutPageable_thenReturnMovies() {
        assertThat(movieRepository.findMoviesByGenre(null, 1L))
                .hasSize(1);
    }

    @Test
    @DisplayName("movieRepository.findMoviesByGenre(Pageable, 1#Science-Fiction)")
    public void whenFindMoviesByGenreWithPageable_thenReturnMovies() {
        assertThat(movieRepository.findMoviesByGenre(PageRequest.of(0, 2), 2L))
                .hasSize(2);
    }

    @Test
    @DisplayName("movieRepository.findMoviesByImdbLessThanEqualAndImdbGreaterThanEqual(null, 8.9, 9.5)")
    public void whenFindMoviesByImdbLessThanEqualAndImdbGreaterThanEqualWithoutPageable_thenReturnMovies() {
        assertThat(movieRepository.findMoviesByImdbLessThanEqualAndImdbGreaterThanEqual(null, 8.9, 9.5))
                .hasSize(2);
    }

    @Test
    @DisplayName("movieRepository.findMoviesByImdbLessThanEqualAndImdbGreaterThanEqual(Pageable, 8.9, 9.5)")
    public void whenFindMoviesByImdbLessThanEqualAndImdbGreaterThanEqualWithPageable_thenReturnMovies() {
        assertThat(movieRepository.findMoviesByImdbLessThanEqualAndImdbGreaterThanEqual(
                PageRequest.of(0, 3),
                7.0,
                9.5))
                .hasSize(3);
    }

}