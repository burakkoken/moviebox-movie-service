package org.codnect.moviebox.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Burak Köken on 3.1.2020.
 */
@Entity
@Table(name = "TvSeries")
public class TvSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tv_series_name")
    private String name;

    @Lob
    @Column(name = "tv_series_overview")
    private String overview;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "TvSeries_Genre",
            joinColumns = { @JoinColumn(name = "tv_series_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(
            mappedBy = "tvSeries",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Season> seasons;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Set<Season> seasons) {
        this.seasons = seasons;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getTvSeries().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getTvSeries().remove(this);
    }

    public void addSeason(Season season) {
        seasons.add(season);
        season.setTvSeries(this);
    }

    public void removeSeason(Season season) {
        seasons.remove(season);
        season.setTvSeries(null);
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TvSeries tvSeries = (TvSeries) obj;
        if(tvSeries.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tvSeries.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
