package org.codnect.moviebox.controller;

import org.codnect.moviebox.dto.CreateGenreDTO;
import org.codnect.moviebox.dto.GenreDTO;
import org.codnect.moviebox.dto.UpdateGenreDTO;
import org.codnect.moviebox.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {

    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDTO> findAllGenres() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO findGenre(@PathVariable("id") Long genreId) {
        return genreService.findGenre(genreId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDTO createGenre(@Valid @RequestBody CreateGenreDTO genreDTO) {
        return genreService.createGenre(genreDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDTO updateGenre(@PathVariable("id") Long genreId, @Valid @RequestBody UpdateGenreDTO genreDTO) {
        return genreService.updateGenre(genreId, genreDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteGenre(@PathVariable("id") Long genreId) {
        genreService.deleteGenre(genreId);
    }

}
