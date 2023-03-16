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
@Table(name = "takeaway")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Takeaway.findAll", query = "SELECT t FROM Takeaway t"),
    @NamedQuery(name = "Takeaway.findById", query = "SELECT t FROM Takeaway t WHERE t.id = :id"),
    @NamedQuery(name = "Takeaway.findByUserId", query = "SELECT t FROM Takeaway t WHERE t.userId = :userId"),
    @NamedQuery(name = "Takeaway.findByTsId", query = "SELECT t FROM Takeaway t WHERE t.tsId = :tsId"),
    @NamedQuery(name = "Takeaway.findByQty", query = "SELECT t FROM Takeaway t WHERE t.qty = :qty"),
    @NamedQuery(name = "Takeaway.findByDateTime", query = "SELECT t FROM Takeaway t WHERE t.dateTime = :dateTime"),
    @NamedQuery(name = "Takeaway.findByPrice", query = "SELECT t FROM Takeaway t WHERE t.price = :price")})
public class Takeaway implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Column(name = "ts_id")
    private Integer tsId;
    @Column(name = "qty")
    private Integer qty;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "date_time")
    private String dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;

    public Takeaway() {
    }

    public Takeaway(Integer id) {
        this.id = id;
    }

    public Takeaway(Integer id, int userId, String dateTime, int price) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getTsId() {
        return tsId;
    }

    public void setTsId(Integer tsId) {
        this.tsId = tsId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
        if (!(object instanceof Takeaway)) {
            return false;
        }
        Takeaway other = (Takeaway) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Takeaway[ id=" + id + " ]";
    }
    
    public String addTakeaway(Takeaway t){
        if (!TakeawayStuff.validateTakeawayStuff(t.getTsId())){
            return "The Takeaway stuff id is invalid!";
        }
        if (!Users.validUserId(t.getUserId())){
            return "The User id is invalid!";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addTakeaway");
            
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tsIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateTimeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("userIdIN", t.getUserId());
            spq.setParameter("tsIdIN", t.getTsId());
            spq.setParameter("qtyIN", t.getQty());
            spq.setParameter("dateTimeIN", t.getDateTime());
            spq.setParameter("priceIN", t.getPrice());
            
            spq.execute();
            
            return "Takeway added successfully.";
            
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
        
    public String updateTakeaway(Takeaway t){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateTakeaway");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("orderIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tsIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", t.getId());
            spq.setParameter("orderIdIN", t.getUserId());
            spq.setParameter("tsIdIN", t.getTsId());
            spq.setParameter("qtyIN", t.getQty());
            
            spq.execute();
            
            return "Takeway added successfully.";
            
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

    public String deleteTakeaway(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteTakeaway");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Takeway deleted successfully.";
            
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
