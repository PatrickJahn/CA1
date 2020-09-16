package facades;

import DTO.JokeDTO;
import utils.EMF_Creator;
import entities.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest {

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    private Joke j1 = new Joke();
    
    public JokeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = JokeFacade.getFacadeExample(emf);
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
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
          j1 = new Joke("Joke1", "More text", "test");
          em.persist(j1);
            em.persist(new Joke("aaa", "bbb", "test"));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    @Test
    public void testGetCount() {
        assertEquals(2, facade.getJokeCount(), "Expects two rows in the database");
    }
    
    
     @Test
    public void testGetByID() {
        JokeDTO joke = facade.getJokeByID(j1.getId());
        assertEquals("Joke1", joke.getJoke());
    }
    
     @Test
    public void testGetByIDNegativ() {
        JokeDTO joke = facade.getJokeByID((long) 1100);
        assertEquals(null, joke.getJoke());
    }
    
    
    @Test
    public void testAddJokes() {
        facade.addJokes();
        assertEquals(3, facade.getJokeCount(), "Expects three rows in the database");
    }
}
