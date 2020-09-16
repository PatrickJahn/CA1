package rest;

import DTO.JokeDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final JokeFacade FACADE =  JokeFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeCount() {
        long count = FACADE.getJokeCount();
       
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<JokeDTO> jokes = FACADE.getAllJokes();
        return GSON.toJson(jokes);
    }
    
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) {
        JokeDTO joke = FACADE.getJokeByID(id);
        if (joke.getJoke() == null) {
         return Response.status(Response.Status.NOT_FOUND).entity("{\"Joke\":\"Not found\"}").build();
        }
        return Response.ok().entity(GSON.toJson(joke)).build();
    }
    
    @Path("random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRandom() {
        JokeDTO joke = FACADE.getRandomJoke();
        if (joke.getJoke() == null) {
         return Response.status(Response.Status.NOT_FOUND).entity("{\"Joke\":\"Not found\"}").build();
        }
        return Response.ok().entity(GSON.toJson(joke)).build();
    }
    
    
    
    @Path("addjokes")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addJokes() {
        FACADE.addJokes();
        return "{\"Jokes\":\"Added\"}";
    }
    
}
