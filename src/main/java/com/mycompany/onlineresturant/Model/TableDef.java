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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "table_def")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableDef.findAll", query = "SELECT t FROM TableDef t"),
    @NamedQuery(name = "TableDef.findById", query = "SELECT t FROM TableDef t WHERE t.id = :id"),
    @NamedQuery(name = "TableDef.findByTableNo", query = "SELECT t FROM TableDef t WHERE t.tableNo = :tableNo"),
    @NamedQuery(name = "TableDef.findByQty", query = "SELECT t FROM TableDef t WHERE t.qty = :qty")})
public class TableDef implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "table_no")
    private int tableNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;

    public TableDef() {
    }

    public TableDef(Integer id) {
        this.id = id;
    }

    public TableDef(Integer id, int tableNo, int qty) {
        this.id = id;
        this.tableNo = tableNo;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        if (!(object instanceof TableDef)) {
            return false;
        }
        TableDef other = (TableDef) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.TableDef[ id=" + id + " ]";
    }
    
    public String defineTable(TableDef td){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("defineTable");
            spq.registerStoredProcedureParameter("tableNo", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            spq.setParameter("tableNo", td.getTableNo());
            spq.setParameter("qtyIN", td.getQty());
            spq.execute();
            
            return "Table define successfully.";
            
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

    public List<TableDef> getAllTables(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<TableDef> tables = new ArrayList(); 
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("allTables");
            List<Object[]> result = spq.getResultList();
            for(Object[] res : result){
                Object idObj = res[0]; 
                String idString = idObj.toString(); 
                Integer id = Integer.parseInt(idString); 
                TableDef t = em.find(TableDef.class, id);
                tables.add(t);
            }
            return tables;
        }
        catch(Exception ex){
            return tables;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }                
    }

    public String deleteTable(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteTable");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Table deleted successfully.";
            
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
    
    public String updateTable(TableDef t){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateTable");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("tablenoIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", t.getId());
            spq.setParameter("tablenoIN", t.getTableNo());
            spq.setParameter("qtyIN", t.getQty());
            
            spq.execute();
            
            return "Table updated successfully.";
            
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
