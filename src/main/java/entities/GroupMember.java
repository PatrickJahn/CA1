package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "GroupMember.deleteAllRows", query = "DELETE from GroupMember")
public class GroupMember implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;
    private String yndlingsFilm;

    public GroupMember(int i, String yoyoyo, String boellebob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getYndlingsFilm() {
        return yndlingsFilm;
    }

    public void setYndlingsFilm(String yndlingsFilm) {
        this.yndlingsFilm = yndlingsFilm;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

     public Long getId() {
        return id;
    }

    
    public GroupMember() {
    }

    public void setId(Long id) {
        this.id = id;
    }
        
   
    
    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo


    public GroupMember( String name, String yndlingsFilm) {
    
        this.name = name;
        this.yndlingsFilm = yndlingsFilm;
       
   }
    
   
}
        