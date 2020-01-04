package org.codnect.moviebox.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Burak Köken on 5.1.2020.
 */
@Entity
@Table(name = "tv_episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tv_episode_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;

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

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Episode episode = (Episode) obj;
        if(episode.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, episode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
