package dtos;

import entities.Actor;
import entities.Movie;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
public class MovieDTO {

    private Integer id;
    private String movieAuthor;
    private Integer moviePt;
    private String movieTitle;
    private Integer movieYear;
    private List<ActorDTO> actors;

    public static List<MovieDTO> getDtos(Set<Movie> movies){
        List<MovieDTO> movieDTOS = new ArrayList(movies);
        return movieDTOS;
    }

    public MovieDTO(Movie movie) {
        if(movie.getId() != null)
            this.id = movie.getId();
        this.movieAuthor = movie.getMovieAuthor();
        this.moviePt = movie.getMoviePt();
        this.movieTitle = movie.getMovieTitle();
        this.movieYear = movie.getMovieYear();
//        this.actors = ActorDTO.getDtos(movie.getActors());
    }

}
