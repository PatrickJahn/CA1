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
            long JokeCount = (long)em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return JokeCount;
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
            if (joke == null){
                return new JokeDTO(new Joke());
            }
            return new JokeDTO(joke);
        }finally{  
            em.close();
        }
    }
     
     
      public void addJokes(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            
            em.persist(new Joke("Banke banke på\n" +
"Hvem der?\n" +
"– Finn\n" +
"Finn hvem?\n" +
"– Finn selv ud af det", "Vitser.dk","Banke Banke På"));
              em.persist(new Joke("Alle børnene gik forbi lorten undtagen Stella hun troede det var Nutella.", "Vitser-jokes.dk","Alle Børnene"));
              em.persist(new Joke("Alle børnene ristede pølser undtagen Niller han ristede sin diller", "Vitser-jokes.dk","AlleBørnene"));
              em.persist(new Joke("Alle børnene kom sikkert hjem fra fabrikken undtagen Ib og Arne de blev til chili konkarne", "Vitser-jokes.dk","Alle Børnene"));
              em.persist(new Joke("Din mor er som en dårlig fodboldkamp… man har ikke lyst til at se på hende.", "Vitser-jokes.dk","Din Mor"));
              em.persist(new Joke("Jeg overvejer at gifte mig med en tysker er det over grænsen?", "Vitser-jokes.dk","Dårlige jokes"));
              em.persist(new Joke("ja, du har ringet til selvmords linjen bliv lige hængende", "Vitser-jokes.dk","Dårlige jokes"));
              em.persist(new Joke("Hvad er ligheden mellem tofu og en dildo? De er begge alternativer til kød.", "bedstejokes.dk","Grove jokes"));
              em.persist(new Joke("Hvordan får du gjort en nonne gravid? Klæd hende ud som en kordreng", "bedstejokes.dk","Grove jokes"));

               em.getTransaction().commit();
        }finally{  
            em.close();
        }
    }
     
}
