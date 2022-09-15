/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.ActorDTO;
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Actor;
import entities.Movie;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        MovieFacade movieFacade = MovieFacade.getMovieFacade(emf);
        ActorFacade actorFacade = ActorFacade.getActorFacade(emf);

        movieFacade.createMovie(new MovieDTO(new Movie("Robert Zemeckis", 81, "Back to the Future", 1985)));
        movieFacade.createMovie(new MovieDTO(new Movie("Robert Zemeckis", 108, "Back to the Future part II", 1989)));

        actorFacade.createActor(new ActorDTO(new Actor(1961, "Michael J. Fox", 'M')));
        actorFacade.createActor(new ActorDTO(new Actor(1938, "Christopher Lloyd", 'M')));
        actorFacade.createActor(new ActorDTO(new Actor(1961, "Lea Thompson", 'F')));




    }
    
    public static void main(String[] args) {
        populate();
    }
}
