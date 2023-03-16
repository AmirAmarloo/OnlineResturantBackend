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
@Table(name = "foods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foods.findAll", query = "SELECT f FROM Foods f"),
    @NamedQuery(name = "Foods.findById", query = "SELECT f FROM Foods f WHERE f.id = :id"),
    @NamedQuery(name = "Foods.findByName", query = "SELECT f FROM Foods f WHERE f.name = :name"),
    @NamedQuery(name = "Foods.findByPrice", query = "SELECT f FROM Foods f WHERE f.price = :price"),
    @NamedQuery(name = "Foods.findByDescription", query = "SELECT f FROM Foods f WHERE f.description = :description"),
    @NamedQuery(name = "Foods.findByCategory", query = "SELECT f FROM Foods f WHERE f.category = :category")})
public class Foods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Size(max = 250)
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private Integer category;

    public Foods() {
    }

    public Foods(Integer id) {
        this.id = id;
    }

    public Foods(Integer id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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
        if (!(object instanceof Foods)) {
            return false;
        }
        Foods other = (Foods) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Foods[ id=" + id + " ]";
    }


    public String addFood(Foods f){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addFood");
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("categoryIN", Integer.class, ParameterMode.IN);
            spq.setParameter("nameIN", f.getName());
            spq.setParameter("priceIN", f.getPrice());
            spq.setParameter("descriptionIN", f.getDescription());
            spq.setParameter("categoryIN", f.getCategory());
            spq.execute();
            
            return "Food registered successfully.";
            
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

    public static Boolean validFoodId(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("validFoodId");
            
            spq.registerStoredProcedureParameter("foodIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("cntResult", Integer.class, ParameterMode.OUT);
            
            spq.setParameter("foodIdIN", id);
            spq.execute();
            
            Integer result = Integer.parseInt(spq.getOutputParameterValue("cntResult").toString());
            
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

    public String updateFood(Foods f){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateFood");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("nameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("categoryIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", f.getId());
            spq.setParameter("nameIN", f.getName());
            spq.setParameter("priceIN", f.getPrice());
            spq.setParameter("descriptionIN", f.getDescription());
            spq.setParameter("categoryIN", f.getCategory());
            
            spq.execute();
            
            return "Food updated successfully.";
            
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

    public String deleteFood(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteFood");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "FOOD deleted successfully.";
            
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
    
    public List<Foods> getAllFoods(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<Foods> foods = new ArrayList(); 
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("allFoods");
            List<Object[]> result = spq.getResultList();
            for(Object[] res : result){
                Object idObj = res[0]; 
                String idString = idObj.toString(); 
                Integer id = Integer.parseInt(idString); 
                Foods f = em.find(Foods.class, id);
                foods.add(f);
            }
            return foods;
        }
        catch(Exception ex){
            return foods;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
                
    }

}
