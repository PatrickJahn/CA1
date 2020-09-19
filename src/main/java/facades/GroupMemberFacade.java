package facades;

import DTO.GroupMemberDTO;
import entities.GroupMember;
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
public class GroupMemberFacade {

    private static GroupMemberFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private GroupMemberFacade() {}
    
    
    
    public static GroupMemberFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }
    
    
  
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    
    /**
    public static GroupMemberFacade createFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GroupMemberFacade();
        }
        return instance;
    }  */

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
 
    
    public List<GroupMemberDTO> getAllGroupMembers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<GroupMember> tq = em.createQuery("SELECT g FROM GroupMember g", GroupMember.class);
            List<GroupMember> groupMember = tq.getResultList();
            List<GroupMemberDTO> groupMemberDTO = new ArrayList<>(); 
            for (GroupMember gm : groupMember){
                groupMemberDTO.add(new GroupMemberDTO(gm));
            }
            return groupMemberDTO;
        }finally{  
            em.close();
        }
        
    }
    public long getGroupMembers(){
        EntityManager em = emf.createEntityManager();
        try{
            long numberOfGroupMembers = (long)em.createQuery("SELECT COUNT(g) FROM GroupMember g").getMaxResults();
            return numberOfGroupMembers;
        }finally{  
            em.close();
        }
        
    }
    
          public void addGroupMembers() {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
            
            em.persist(new GroupMember(1, "JegHettarMartin", "HawwyPotter"));
            em.persist(new GroupMember(2, "Poul, Poul Pott", "King Cock"));
            em.persist(new GroupMember(3, "Jeppe Kofoed", "Videoer af 15årige piger hvis jeg også kan knalde dem?"));
          
             

               em.getTransaction().commit();
        }finally{  
            em.close();
        }
    }

    
     public GroupMemberDTO getGroupMemberById(Long id){
        EntityManager em = emf.createEntityManager();
        try{
            GroupMember groupMember = em.find(GroupMember.class, id);
            return new GroupMemberDTO(groupMember);
        }finally{  
            em.close();
        }
    }
     
     
     
     
}
