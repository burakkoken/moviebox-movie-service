package org.codnect.moviebox.dto;

import javax.validation.constraints.*;
import java.util.Date;

public class CreateMovieDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String overview;

    @Min(0)
    private Integer runtime;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double imdb;

    @NotNull
    private Date releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Double getImdb() {
        return imdb;
    }

    public void setImdb(Double imdb) {
        this.imdb = imdb;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}
