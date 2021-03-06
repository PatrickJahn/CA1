package rest;
import DTO.CarDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.CarFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("Car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final CarFacade FACADE =  CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
           
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
     @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarCount() {
        long count = FACADE.getCount();  
        return "{\"count\":"+count+"}"; 
    }
   @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes() {
        List<CarDTO> cars = FACADE.getAllCars();
        return GSON.toJson(cars);
    }
    @Path("addcars")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String addCars() {
        FACADE.addCars();
        return "{\"Cars\":\"Added\"}";
    }
    
}
