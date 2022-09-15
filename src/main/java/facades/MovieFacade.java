package facades;

import dtos.ActorDTO;
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Actor;
import entities.Movie;
import entities.RenameMe;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

//import errorhandling.RenameMeNotFoundException;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MovieDTO createMovie(MovieDTO movieDTO) {

        Movie movieEntity = new Movie(movieDTO.getMovieAuthor(), movieDTO.getMoviePt(), movieDTO.getMovieTitle(), movieDTO.getMovieYear());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movieEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return movieDTO;
    }

    public Long countMovies() {
        EntityManager em = getEntityManager();

        TypedQuery<Long> query = em.createQuery("select count(m) from Movie m", Long.class);
        return query.getSingleResult();
    }

    public List<MovieDTO> getAllMovies(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<MovieDTO> queryM = em.createQuery("select NEW dtos.MovieDTO(m) from Movie m", MovieDTO.class);
        List<MovieDTO> mDtos = queryM.getResultList();
        for (MovieDTO md : mDtos){
            TypedQuery<ActorDTO> queryA = em.createQuery("select DISTINCT NEW dtos.ActorDTO(a) from Actor a " +
                    "join a.movies m " +
                    "join m.actors ac where m.id = :id", ActorDTO.class);
            queryA.setParameter("id", md.getId());
            md.setActors(queryA.getResultList());
        }
        return mDtos;
    }

    public MovieDTO getMovieById (int id) {
            EntityManager em = emf.createEntityManager();
            Movie movie = em.find(Movie.class, id);
            if (movie == null)
                return null;
            return new MovieDTO(movie);
        }

        public MovieDTO addActorToMovie (int movieId, int actorId) {
        EntityManager em = emf.createEntityManager();
        Movie movie = em.find(Movie.class, movieId);
        Actor actor = em.find(Actor.class, actorId);

        if ( movie != null && actor != null) {
            movie.assignActor(actor);
        }
            try {
                em.getTransaction().begin();
                em.persist(movie);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        return new MovieDTO(movie);
        }
    }

