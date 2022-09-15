package facades;

import dtos.MovieDTO;
import entities.Actor;
import entities.Movie;
import utils.EMF_Creator;
import entities.RenameMe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Actor.deleteAllRows").executeUpdate();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie("Frank", 180, "Filmnavn", 2000));
            em.persist(new Movie("Jens", 180, "Filmnavn2", 2020));
            em.persist(new Actor(10, "Peter", 'm'));
            em.persist(new Actor(12, "Michael", 'm'));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testMovieCount() throws Exception {
        assertEquals(2, facade.countMovies(), "Expects two movies in the database");
    }

    @Test
    public void testAddActorToMovie() throws Exception {
        int actorId = 2;
        int movieId = 1;
        EntityManager em = emf.createEntityManager();


        facade.addActorToMovie(movieId,actorId);
        Movie movie = em.find(Movie.class, movieId);

        assertEquals(1, movie.getActors().size());
        }

    }
