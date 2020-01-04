package org.codnect.moviebox.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@Entity
@Table(name = "TvSeasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tv_season_no")
    private Integer seasonNo;

    @Column(name = "tv_season_name")
    private String name;

    @Lob
    @Column(name = "tv_season_overview")
    private String overview;

    @Temporal(TemporalType.DATE)
    @Column(name = "tv_season_air_date")
    private Date airDate;


    @OneToMany(
            mappedBy = "season",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Episode> episodes = new HashSet<>();

    @ManyToOne
    private TvSeries tvSeries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeasonNo() {
        return seasonNo;
    }

    public void setSeasonNo(Integer seasonNo) {
        this.seasonNo = seasonNo;
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

    public Date getAirDate() {
        return airDate;
    }

    public void setAirDate(Date airDate) {
        this.airDate = airDate;
    }

    public Set<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Set<Episode> episodes) {
        this.episodes = episodes;
    }

    public TvSeries getTvSeries() {
        return tvSeries;
    }

    public void setTvSeries(TvSeries tvSeries) {
        this.tvSeries = tvSeries;
    }

    public void addEpisode(Episode episode) {
        episodes.add(episode);
        episode.setSeason(this);
    }

    public void removeEpisode(Episode episode) {
        episodes.remove(episode);
        episode.setSeason(null);
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Season season = (Season) obj;
        if(season.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, season.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
