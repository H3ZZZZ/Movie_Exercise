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
public class ActorDTO {

    private Integer id;
    private Integer actorAge;
    private String actorName;
    private Character actorSex;
    private List<MovieDTO> movies;
    public static List<ActorDTO> getDtos(Set<Actor> actors){
        List<ActorDTO> actorDTOS = new ArrayList(actors);
        return actorDTOS;
    }

    public ActorDTO (Actor actor){
        if (actor.getId() != null)
            this.id = actor.getId();
            this.actorAge = actor.getActorAge();
            this.actorName = actor.getActorName();
            this.actorSex = actor.getActorSex();
    //            this.movies = MovieDTO.getDtos(actor.getMovies());
    }



}
