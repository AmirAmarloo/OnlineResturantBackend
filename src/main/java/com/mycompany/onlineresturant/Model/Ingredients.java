/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.onlineresturant.Model;

import com.mycompany.onlineresturant.Configuration.Database;
import java.io.Serializable;
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
@Table(name = "ingredients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingredients.findAll", query = "SELECT i FROM Ingredients i"),
    @NamedQuery(name = "Ingredients.findById", query = "SELECT i FROM Ingredients i WHERE i.id = :id"),
    @NamedQuery(name = "Ingredients.findByMaterialName", query = "SELECT i FROM Ingredients i WHERE i.materialName = :materialName"),
    @NamedQuery(name = "Ingredients.findByAmount", query = "SELECT i FROM Ingredients i WHERE i.amount = :amount"),
    @NamedQuery(name = "Ingredients.findByUnit", query = "SELECT i FROM Ingredients i WHERE i.unit = :unit"),
    @NamedQuery(name = "Ingredients.findByPrice", query = "SELECT i FROM Ingredients i WHERE i.price = :price"),
    @NamedQuery(name = "Ingredients.findByFoodId", query = "SELECT i FROM Ingredients i WHERE i.foodId = :foodId")})
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "material_name")
    private String materialName;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "unit")
    private Integer unit;
    @Column(name = "price")
    private Integer price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "food_id")
    private int foodId;

    public Ingredients() {
    }

    public Ingredients(Integer id) {
        this.id = id;
    }

    public Ingredients(Integer id, String materialName, int foodId) {
        this.id = id;
        this.materialName = materialName;
        this.foodId = foodId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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
        if (!(object instanceof Ingredients)) {
            return false;
        }
        Ingredients other = (Ingredients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Ingredients[ id=" + id + " ]";
    }
    
    public String addIngredients(Ingredients i){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addIngreds");
            
            spq.registerStoredProcedureParameter("matNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("amountIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("unitIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foodIdIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("matNameIN", i.getMaterialName());
            spq.setParameter("amountIN", i.getAmount());
            spq.setParameter("unitIN", i.getUnit());
            spq.setParameter("priceIN", i.getPrice());
            spq.setParameter("foodIdIN", i.getFoodId());
            
            spq.execute();
            
            return "Ingredients registered successfully.";
            
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
    
    public String updateIngredients(Ingredients i){
        if (!Foods.validFoodId(i.getFoodId())){
            return "The food Id is invalid!";
        }        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateIngredient");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("materialNmaeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("unitIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foodidIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", i.getId());
            spq.setParameter("materialNmaeIN", i.getMaterialName());
            spq.setParameter("unitIN", i.getUnit());
            spq.setParameter("priceIN", i.getPrice());
            spq.setParameter("foodidIN", i.getFoodId());
            
            spq.execute();
            
            return "Ingredients updated successfully.";
            
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

    public String deleteIngredients(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteIngredient");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Ingredients deleted successfully.";
            
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

    
    
}
