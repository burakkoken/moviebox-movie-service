package org.codnect.moviebox.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Burak Köken on 2.1.2020.
 */
@Entity
@Table(name = "Genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Column(name = "genre_name")
    private String name;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<TvSeries> tvSeries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    public Set<TvSeries> getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(Set<TvSeries> tvSeries) {
        this.tvSeries = tvSeries;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Genre genre = (Genre) obj;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
