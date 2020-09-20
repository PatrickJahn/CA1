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
         public void addCars(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            
            em.persist(new Car("Volvo","V40",2004,"Diesel",80000,12));
            em.persist(new Car("Volvo","XC90",2020,"Gasoline",350000,20));
            em.persist(new Car("Fiat","500",2012,"Gasoline",45000,30));
            em.persist(new Car("Fiat","Bravo",2008,"Diesel",32000,54));
            em.persist(new Car("Fiat","Panda",2020,"Hybrid",200000,40));
            em.persist(new Car("VW","E-UP",2020,"Electirc",320000,20));
            em.persist(new Car("VW","Touran",2015,"Diesel",84000,32));
            em.persist(new Car("Seat","Arona",2015,"Gasoline",180000,34));
            em.persist(new Car("Seat","Leon",2008,"Gasoline",35000,12));
            em.persist(new Car("Toyota","Rav-4",2020,"Hybrid",400000,58));
            em.persist(new Car("Renault","Zoe",2015,"Electric",12450,83));
            em.persist(new Car("Renault","Captur",2012,"Diesel",99900,44));
        
            
            em.getTransaction().commit();
        }finally{  
            em.close();
        }
    }

}
