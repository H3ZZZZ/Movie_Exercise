package facades;

import dtos.ActorDTO;
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Actor;
import entities.Movie;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class ActorFacade {

    private static ActorFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private ActorFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static ActorFacade getActorFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ActorFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ActorDTO createActor(ActorDTO actorDTO) {

        Actor actorEntity = new Actor(actorDTO.getActorAge(), actorDTO.getActorName(), actorDTO.getActorSex());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(actorEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return actorDTO;
    }
    


}
