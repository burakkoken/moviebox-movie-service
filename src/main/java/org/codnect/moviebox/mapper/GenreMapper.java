package org.codnect.moviebox.mapper;

import org.codnect.moviebox.dto.CreateGenreDTO;
import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.dto.UpdateGenreDTO;
import org.codnect.moviebox.model.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDTO toGenreDTO(Genre genre);

    Genre toGenre(GenreDTO genreDTO);

    List<GenreDTO> toGenreDTOList(List<Genre> genres);

    List<Genre> toGenreList(List<GenreDTO> genreDTOList);

    Genre toGenre(CreateGenreDTO genreDTO);

    Genre toGenre(UpdateGenreDTO genreDTO);

}