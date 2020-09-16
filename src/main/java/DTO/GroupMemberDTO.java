/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import entities.GroupMember;
/**
 *
 * @author Madsj
 */
public class GroupMemberDTO {
    
    private String name; 
    private String yndlingsFilm;
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYndlingsFilm() {
        return yndlingsFilm;
    }

    public void setYndlingsFilm(String yndlingsFilm) {
        this.yndlingsFilm = yndlingsFilm;
    }
    
    public GroupMemberDTO(){
    }

    public GroupMemberDTO(GroupMember groupMember) {
        this.name = groupMember.getName();
        this.yndlingsFilm= groupMember.getYndlingsFilm();
    }



    @Override
    public String toString() {
        return "GroupMemberDTO{" + " name=" + name + ", yndlingsFilm=" + yndlingsFilm + '}';
                
    }
    
    
}

    

