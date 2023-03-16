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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "deliver")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deliver.findAll", query = "SELECT d FROM Deliver d"),
    @NamedQuery(name = "Deliver.findById", query = "SELECT d FROM Deliver d WHERE d.id = :id"),
    @NamedQuery(name = "Deliver.findByUserId", query = "SELECT d FROM Deliver d WHERE d.userId = :userId"),
    @NamedQuery(name = "Deliver.findByByWho", query = "SELECT d FROM Deliver d WHERE d.byWho = :byWho")})
public class Deliver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 100)
    @Column(name = "by_who")
    private String byWho;

    public Deliver() {
    }

    public Deliver(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getByWho() {
        return byWho;
    }

    public void setByWho(String byWho) {
        this.byWho = byWho;
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
        if (!(object instanceof Deliver)) {
            return false;
        }
        Deliver other = (Deliver) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Deliver[ id=" + id + " ]";
    }
    
    public String addDeliver(Deliver d){
        if (!Users.validUserId(d.getUserId())){
            System.err.println(d.getUserId());
            return "The User Id is invalid!";
        }
        System.out.println("Before execute.");
        System.out.println(d.toString());
        System.err.println(d.getUserId());
        System.err.println(d.getByWho());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();


        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addDeliver");
            
            spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("byWhoIN", String.class, ParameterMode.IN);
            
            spq.setParameter("useridIN", d.getUserId());
            spq.setParameter("byWhoIN", d.getByWho());
            
            spq.execute();
            System.out.println("After execute");
            return "Deliver registered successfully.";
            
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
    
    public String updateDeliver(Deliver d){
        if (!Users.validUserId(d.getUserId())){
            return "The User Id is invalid!";
        }        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateDeliver");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("byWhoIN", String.class, ParameterMode.IN);
            
            spq.setParameter("idIN", d.getId());
            spq.setParameter("useridIN", d.getUserId());
            spq.setParameter("byWhoIN", d.getByWho());
            
            spq.execute();
            
            return "Deliver updated successfully.";
            
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

    public String deleteDeleiver(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteDeliver");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Deliver deleted successfully.";
            
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
