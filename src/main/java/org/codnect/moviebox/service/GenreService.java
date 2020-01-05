package org.codnect.moviebox.service;
import org.codnect.moviebox.dto.CreateGenreDTO;
import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.dto.UpdateGenreDTO;
import org.codnect.moviebox.exception.GenreDuplicateException;
import org.codnect.moviebox.exception.GenreNotFoundException;
import org.codnect.moviebox.mapper.GenreMapper;
import org.codnect.moviebox.model.Genre;
import org.codnect.moviebox.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GenreService {

    private GenreRepository genreRepository;
    private GenreMapper genreMapper;

    public GenreService(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    public List<GenreDTO> findAll() {
        return genreMapper.toGenreDTOList(genreRepository.findAll());
    }

    public GenreDTO findGenre(Long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        return genreMapper.toGenreDTO(
                optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId))
        );
    }

    public GenreDTO createGenre(CreateGenreDTO genreDTO) {
        Optional<Genre> optionalGenre = genreRepository.findByName(genreDTO.getName());
        if(optionalGenre.isPresent()) {
            throw new GenreDuplicateException(genreDTO.getName());
        }
        Genre newGenre = genreMapper.toGenre(genreDTO);
        return genreMapper.toGenreDTO(genreRepository.save(newGenre));
    }

    public GenreDTO updateGenre(Long genreId, UpdateGenreDTO genreDTO) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId));
        optionalGenre = genreRepository.findByName(genreDTO.getName());
        if(optionalGenre.isPresent()) {
            throw new GenreDuplicateException(genreDTO.getName());
        }
        Genre updatedGenre = genreMapper.toGenre(genreDTO);
        updatedGenre.setId(genreId);
       return genreMapper.toGenreDTO(genreRepository.save(updatedGenre));
    }

    public void deleteGenre(Long genreId) {
        Optional<Genre> optionalGenre = genreRepository.findById(genreId);
        Genre genre = optionalGenre.orElseThrow(() -> new GenreNotFoundException(genreId));
        genreRepository.delete(genre);
    }

}
