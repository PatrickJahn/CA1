package facades;

import DTO.GroupMemberDTO;
import utils.EMF_Creator;
import entities.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled

public class GroupMemberFacadeTest {

    private static EntityManagerFactory emf;
    private static GroupMemberFacade facade;
    private GroupMember gm = new GroupMember();

    public GroupMemberFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = GroupMemberFacade.getFacadeExample(emf);
    }
    
  
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
   @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
          gm = new GroupMember( "yoyoyo", "boellebob");
          em.persist(gm);
            em.persist(new GroupMember( "kim", "Jane Bond"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }
/*
    @Test
    public void testAddGroupMembers() {

        assertEquals(2, facade.getGroupMembersCount(), "Expects 2 rows in the database");
    }
 
*/
}
