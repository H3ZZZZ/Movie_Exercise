package entities;

import dtos.MovieDTO;
import dtos.RenameMeDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NamedQuery(name = "Movie.deleteAllRows", query = "delete from Movie")

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "movie_author", nullable = false, length = 45)
    private String movieAuthor;

    @NotNull
    @Column(name = "movie_pt", nullable = false)
    private Integer moviePt;

    @Size(max = 45)
    @NotNull
    @Column(name = "movie_title", nullable = false, length = 45)
    private String movieTitle;

    @NotNull
    @Column(name = "movie_year", nullable = false)
    private Integer movieYear;

    @ManyToMany
    @JoinTable(name = "actor_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors = new LinkedHashSet<>();

    public Movie() {
    }

    public Movie(String movieAuthor, Integer moviePt, String movieTitle, Integer movieYear) {
        this.movieAuthor = movieAuthor;
        this.moviePt = moviePt;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
    }

    public void assignActor(Actor actor) {
        if(actor != null)
            this.actors.add(actor);
    }

}