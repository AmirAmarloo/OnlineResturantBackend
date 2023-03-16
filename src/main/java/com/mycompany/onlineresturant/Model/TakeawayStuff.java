/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineresturant.Model;

import com.mycompany.onlineresturant.Configuration.Database;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "takeaway_stuff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TakeawayStuff.findAll", query = "SELECT t FROM TakeawayStuff t"),
    @NamedQuery(name = "TakeawayStuff.findById", query = "SELECT t FROM TakeawayStuff t WHERE t.id = :id"),
    @NamedQuery(name = "TakeawayStuff.findByDescription", query = "SELECT t FROM TakeawayStuff t WHERE t.description = :description"),
    @NamedQuery(name = "TakeawayStuff.findByPrice", query = "SELECT t FROM TakeawayStuff t WHERE t.price = :price")})
public class TakeawayStuff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;

    public TakeawayStuff() {
    }

    public TakeawayStuff(Integer id) {
        this.id = id;
    }

    public TakeawayStuff(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TakeawayStuff)) {
            return false;
        }
        TakeawayStuff other = (TakeawayStuff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.TakeawayStuff[ id=" + id + " ]";
    }
    
    public String addTakeawayStuff(TakeawayStuff t){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addTakewayStuff");
            
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("descriptionIN", t.getDescription());
            spq.setParameter("priceIN", t.getPrice());
            
            spq.execute();
            
            return "Takeway Stuff added successfully.";
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public String updateTakeawayStuff(TakeawayStuff t){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateTakeawayStuff");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("descriptionIN", t.getDescription());
            spq.setParameter("priceIN", t.getPrice());
            spq.setParameter("idIN", t.getId());
            
            spq.execute();
            
            return "Takeway Stuff updated successfully.";
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }

    public String deleteTakeawayStuff(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteTakeawayStuff");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Takeway Stuff deleted successfully.";
            
        }
        catch(Exception ex){
            return ex.getMessage();
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public static Boolean validateTakeawayStuff(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("validateTakeawayStuff");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("cntId", Integer.class, ParameterMode.OUT);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("cntId").toString());
            
            
            return result == 1? true : false;
            
        }
        catch(Exception ex){
            return false;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
        
    }
    
    public List<TakeawayStuff> getAllTakeawayStuff(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<TakeawayStuff> takeawayStuff = new ArrayList(); 
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("allTakeawayStuff");
            List<Object[]> result = spq.getResultList();
            for(Object[] res : result){
                Object idObj = res[0]; 
                String idString = idObj.toString(); 
                Integer id = Integer.parseInt(idString); 
                TakeawayStuff tf = em.find(TakeawayStuff.class, id);
                takeawayStuff.add(tf);
            }
            return takeawayStuff;
        }
        catch(Exception ex){
            return takeawayStuff;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
                
    }
    
    
}
