package facades;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import DTO.CarDTO;
import entities.Car;
import java.util.ArrayList;
import javax.persistence.TypedQuery;
/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CarFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
 
    public long getCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
       public List<CarDTO> getAllCars(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = query.getResultList();
            List<CarDTO> CarDTO = new ArrayList<>(); 
            cars.forEach(c -> {
                CarDTO.add(new CarDTO(c));
            });
            return CarDTO;
        }finally{  
            em.close();
        }
        
    }

}
