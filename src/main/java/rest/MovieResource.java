package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import dtos.RenameMeDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "Hvad er forskellen på en kanin?";
    }

    @PUT
    @Path("/update/{movieId}/{actorId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addActorToMovie(@PathParam("movieId") int movieId, @PathParam("actorId") int actorId){

        FACADE.addActorToMovie(movieId, actorId);
        MovieDTO m = FACADE.getMovieById(movieId);
        return Response.ok().entity(GSON.toJson(m)).build();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPerson(@PathParam("id") int id){
        MovieDTO m = FACADE.getMovieById(id);
        return Response.ok().entity(GSON.toJson(m)).build();
    }

    @Path("/count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieCount() {

        long count = FACADE.countMovies().longValue();
        return "{\"count\":"+count+"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllMovies() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllMovies())).build();
    }

//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response postExample(String input){
//        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
//        System.out.println(rmdto);
//        return Response.ok().entity(rmdto).build();
//    }
}
