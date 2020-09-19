package rest;

import DTO.GroupMemberDTO;
import facades.GroupMemberFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("GroupMember")
public class GroupMemberResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final GroupMemberFacade FACADE =  GroupMemberFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
 
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<GroupMemberDTO> allGroupMembers = FACADE.getAllGroupMembers();
        //System.out.println("--------------->"+count);
        return GSON.toJson(allGroupMembers);
    }
}
