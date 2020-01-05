package org.codnect.moviebox.service;

import org.codnect.moviebox.dto.CreateMovieDTO;
import org.codnect.moviebox.dto.MovieDTO;
import org.codnect.moviebox.dto.UpdateMovieDTO;
import org.codnect.moviebox.exception.MovieNotFoundException;
import org.codnect.moviebox.mapper.MovieMapper;
import org.codnect.moviebox.model.Movie;
import org.codnect.moviebox.repository.MovieRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {

    private MovieRepository movieRepository;
    private MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    public List<MovieDTO> findAllMovies(Pageable pageable) {
        return movieMapper.toMovieDTOList(movieRepository.findAllMovies(pageable));
    }

    public MovieDTO findMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        return movieMapper.toMovieDTO(
                optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId))
        );
    }

    public MovieDTO createMovie(CreateMovieDTO movieDTO) {
        Movie movie = movieMapper.toMovie(movieDTO);
        return movieMapper.toMovieDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(Long movieId, UpdateMovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        Movie updatedMovie = movieMapper.toMovie(movieDTO);
        updatedMovie.setId(movieId);
        return movieMapper.toMovieDTO(movieRepository.save(movieRepository.save(updatedMovie)));
    }

    public void deleteMovie(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie = optionalMovie.orElseThrow(() -> new MovieNotFoundException(movieId));
        movieRepository.delete(movie);
    }

}
