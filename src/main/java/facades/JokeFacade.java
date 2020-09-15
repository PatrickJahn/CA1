package facades;

import DTO.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private JokeFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static JokeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
 
    public long getJokeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<JokeDTO> getAllJokes(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Joke> tq = em.createQuery("SELECT j FROM Joke j", Joke.class);
            List<Joke> jokes = tq.getResultList();
            List<JokeDTO> jokesDTO = new ArrayList<>(); 
            for (Joke j : jokes){
                jokesDTO.add(new JokeDTO(j));
            }
            return jokesDTO;
        }finally{  
            em.close();
        }
        
    }

    
     public JokeDTO getJokeByID(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            Joke joke = em.find(Joke.class, id);
            return new JokeDTO(joke);
        }finally{  
            em.close();
        }
    }
     
     
     
     
}
