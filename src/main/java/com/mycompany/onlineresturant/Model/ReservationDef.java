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
@Table(name = "reservation_def")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservationDef.findAll", query = "SELECT r FROM ReservationDef r"),
    @NamedQuery(name = "ReservationDef.findById", query = "SELECT r FROM ReservationDef r WHERE r.id = :id"),
    @NamedQuery(name = "ReservationDef.findByTableId", query = "SELECT r FROM ReservationDef r WHERE r.tableId = :tableId"),
    @NamedQuery(name = "ReservationDef.findByDateTime", query = "SELECT r FROM ReservationDef r WHERE r.dateTime = :dateTime"),
    @NamedQuery(name = "ReservationDef.findByUserId", query = "SELECT r FROM ReservationDef r WHERE r.userId = :userId"),
    @NamedQuery(name = "ReservationDef.findByQty", query = "SELECT r FROM ReservationDef r WHERE r.qty = :qty"),
    @NamedQuery(name = "ReservationDef.findByStatus", query = "SELECT r FROM ReservationDef r WHERE r.status = :status")})
public class ReservationDef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "table_id")
    private int tableId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "date_time")
    private String dateTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;

    public ReservationDef() {
    }

    public ReservationDef(Integer id) {
        this.id = id;
    }

    public ReservationDef(Integer id, int tableId, String dateTime, int userId, int qty, int status) {
        this.id = id;
        this.tableId = tableId;
        this.dateTime = dateTime;
        this.userId = userId;
        this.qty = qty;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof ReservationDef)) {
            return false;
        }
        ReservationDef other = (ReservationDef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.ReservationDef[ id=" + id + " ]";
    }

    public String defineReservtion(ReservationDef r){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("defineReservation");
            spq.registerStoredProcedureParameter("tableidIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("useridIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("isdefinedOUT", String.class, ParameterMode.OUT);
            spq.setParameter("tableidIN", r.getTableId());
            spq.setParameter("dateIN", r.getDateTime());
            spq.setParameter("useridIN", r.getUserId());
            spq.setParameter("qtyIN", r.getQty());
            spq.execute();
            
            
            return spq.getOutputParameterValue("isdefinedOUT").toString();
            
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
