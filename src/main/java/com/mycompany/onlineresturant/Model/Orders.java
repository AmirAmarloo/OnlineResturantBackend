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
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByUserId", query = "SELECT o FROM Orders o WHERE o.userId = :userId"),
    @NamedQuery(name = "Orders.findByFoodId", query = "SELECT o FROM Orders o WHERE o.foodId = :foodId"),
    @NamedQuery(name = "Orders.findByPrice", query = "SELECT o FROM Orders o WHERE o.price = :price"),
    @NamedQuery(name = "Orders.findByQty", query = "SELECT o FROM Orders o WHERE o.qty = :qty"),
    @NamedQuery(name = "Orders.findByDescription", query = "SELECT o FROM Orders o WHERE o.description = :description"),
    @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
    @NamedQuery(name = "Orders.findByDateTime", query = "SELECT o FROM Orders o WHERE o.dateTime = :dateTime")})
public class Orders implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "food_id")
    private int foodId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qty")
    private int qty;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Size(max = 50)
    @Column(name = "date_time")
    private String dateTime;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, int userId, int foodId, int price, int qty, int status) {
        this.id = id;
        this.userId = userId;
        this.foodId = foodId;
        this.price = price;
        this.qty = qty;
        this.status = status;
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

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.onlineresturant.Model.Orders[ id=" + id + " ]";
    }

    public String addOrders(Orders o){
        if (!Users.validUserId(o.getUserId())){
            return "User Id is invalid!";
        }
        if (!Foods.validFoodId(o.getFoodId())){
            return "Food Id is invalid!";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("addOrder");
            
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foodIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descIN", String.class, ParameterMode.IN);
            
            spq.setParameter("userIdIN", o.getUserId());
            spq.setParameter("foodIdIN", o.getFoodId());
            spq.setParameter("qtyIN", o.getQty());
            spq.setParameter("descIN", o.getDescription());
            
            spq.execute();
            
            return "Order successfully added.";
            
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
    
    public static Boolean validateOrders(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("validateOrders");
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

    public String updateOrders(Orders o){
        if (!Users.validUserId(o.getUserId())){
            return "User Id is invalid!";
        }
        if (!Foods.validFoodId(o.getFoodId())){
            return "Food Id is invalid!";
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("updateOrders");
            
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("foodIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("priceIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("qtyIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("descriptionIN", String.class, ParameterMode.IN);
            
            spq.setParameter("idIN", o.getId());
            spq.setParameter("userIdIN", o.getUserId());
            spq.setParameter("foodIdIN", o.getFoodId());
            spq.setParameter("priceIN", o.getPrice());
            spq.setParameter("qtyIN", o.getQty());
            spq.setParameter("descriptionIN", o.getDescription());
            
            spq.execute();
            
            return "Order updated successfully.";
            
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

    public String deleteOrder(int delId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("deleteOrder");
            spq.registerStoredProcedureParameter("idIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("idIN", delId);
            
            spq.execute();
            
            return "Order deleted successfully.";
            
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
    
    public String submitOrder(int userId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("submitOrder");
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("submitTimeOut", String.class, ParameterMode.OUT);
            
            spq.setParameter("userIdIN", userId);
            
            spq.execute();
            String result = spq.getOutputParameterValue("submitTimeOut").toString();

            
            return result;
            
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
    
    public List<OrderQuery> getOrders(int stat){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<OrderQuery> orders = new ArrayList(); 
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getOrder");
            spq.registerStoredProcedureParameter("statusIN", Integer.class, ParameterMode.IN);
            spq.setParameter("statusIN", stat);
            
            List<Object[]> result = spq.getResultList();
            for (int i = 0; i<result.size(); i++){
                Object[] myRes = result.get(i);
                Object idObj = myRes[0];
                String idIn = idObj.toString();
                Object idObj1 = myRes[1];
                String nameIn = idObj1.toString();
                Object idObj2 = myRes[2];
                String priceIn = idObj2.toString();
                Object idObj3 = myRes[3];
                String qtyIn = idObj3.toString();
                Object idObj4 = myRes[4];
                String descriptionIn = idObj4.toString();
                Object idObj5 = myRes[5];
                String categoryIn = idObj5.toString();
                OrderQuery o = new OrderQuery(Integer.parseInt(idIn), nameIn, Integer.parseInt(priceIn), Integer.parseInt(qtyIn), descriptionIn, Integer.parseInt(categoryIn));
                orders.add(o);
            }

            return orders;
        }
        catch(Exception ex){
            return orders;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }
    }
    
    public String changeStatus(int userId, String datetimeOrder, int statusOrder){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changeStatus");
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("dateTimeIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("statusIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("userIdIN", userId);
            spq.setParameter("dateTimeIN", datetimeOrder);
            spq.setParameter("statusIN", statusOrder);
            
            spq.execute();

            return "Status Changed";
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
    
    public List<OrderByStatus> getOrderByStatus(int statusOrder){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<OrderByStatus> orders = new ArrayList();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getOrderByStatus");
            spq.registerStoredProcedureParameter("statusIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("statusIN", statusOrder);
            List<Object[]> result = spq.getResultList();
            for (int i = 0; i<result.size(); i++){
                Object[] myRes = result.get(i);
                Object idObj0 = myRes[0];
                String idIn = idObj0.toString();
                Object idObj1 = myRes[1];
                String nameIn = idObj1.toString();
                Object idObj2 = myRes[2];
                String qtyIn = idObj2.toString();
                Object idObj3 = myRes[3];
                String descriptionIn = idObj3.toString();
                Object idObj4 = myRes[4];
                String userIdIn = idObj4.toString();
                Object idObj5 = myRes[5];
                String dateTimeIn = idObj5.toString();
                Object idObj6 = myRes[6];
                String orderGroupIn = idObj6.toString();
                OrderByStatus o = new OrderByStatus(Integer.parseInt(idIn), nameIn, Integer.parseInt(qtyIn), descriptionIn, Integer.parseInt(userIdIn), dateTimeIn, Integer.parseInt(orderGroupIn));
                orders.add(o);
            }
            return orders;
        }
        catch(Exception ex){
            return orders;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }        
    }
    
    public List<PeriodicReportDetail> periodicReportDetails(int periodTime){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<PeriodicReportDetail> repotData = new ArrayList();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("periodicReportDetail");
            spq.registerStoredProcedureParameter("periodIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("periodIN", periodTime);
            List<Object[]> result = spq.getResultList();
            for (int i = 0; i < result.size(); i++){
                Object[] myRes = result.get(i);
                Object idObj0 = myRes[0];
                String idIn = idObj0.toString();
                Object idObj1 = myRes[1];
                String dateTimeIn = idObj1.toString();
                Object idObj2 = myRes[2];
                String foodNameIn = idObj2.toString();
                Object idObj3 = myRes[3];
                String orderPriceIn = idObj3.toString();
                Object idObj4 = myRes[4];
                String orderQTYIn = idObj4.toString();
                Object idObj5 = myRes[5];
                String totalOrderPriceIn = idObj5.toString();
                Object idObj6 = myRes[6];
                String categoryIn = idObj6.toString();
                Object idObj7 = myRes[7];
                String takeawayPriceIn = idObj7.toString();
                Object idObj8 = myRes[8];
                String takeawayQtyIn = idObj8.toString();
                Object idObj9 = myRes[9];
                String twTotalPriceIn = idObj9.toString();
                Object idObj10 = myRes[10];
                String totalPriceIn = idObj10.toString();
                Object idObj11 = myRes[11];
                String grossProfitIn = idObj11.toString();
                PeriodicReportDetail o = new PeriodicReportDetail(Integer.parseInt(idIn), 
                        dateTimeIn, 
                        foodNameIn, 
                        Integer.parseInt(orderPriceIn), 
                        Integer.parseInt(orderQTYIn), 
                        Integer.parseInt(totalOrderPriceIn), 
                        Integer.parseInt(categoryIn), 
                        Integer.parseInt(takeawayPriceIn), 
                        Integer.parseInt(takeawayQtyIn), 
                        Integer.parseInt(twTotalPriceIn), 
                        Integer.parseInt(totalPriceIn), 
                        Integer.parseInt(grossProfitIn));
                repotData.add(o);
            }
            return repotData;
        }
        catch(Exception ex){
            return repotData;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }        
    }
    
    public List<PeriodicReportDetail> periodicReportDetailsByDate(String dateIn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<PeriodicReportDetail> repotData = new ArrayList();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("periodicReportDetailByDate");
            spq.registerStoredProcedureParameter("dateIN", String.class, ParameterMode.IN);
            
            spq.setParameter("dateIN", dateIn);
            List<Object[]> result = spq.getResultList();
            for (int i = 0; i < result.size(); i++){
                Object[] myRes = result.get(i);
                Object idObj0 = myRes[0];
                String idIn = idObj0.toString();
                Object idObj1 = myRes[1];
                String dateTimeIn = idObj1.toString();
                Object idObj2 = myRes[2];
                String foodNameIn = idObj2.toString();
                Object idObj3 = myRes[3];
                String orderPriceIn = idObj3.toString();
                Object idObj4 = myRes[4];
                String orderQTYIn = idObj4.toString();
                Object idObj5 = myRes[5];
                String totalOrderPriceIn = idObj5.toString();
                Object idObj6 = myRes[6];
                String categoryIn = idObj6.toString();
                Object idObj7 = myRes[7];
                String takeawayPriceIn = idObj7.toString();
                Object idObj8 = myRes[8];
                String takeawayQtyIn = idObj8.toString();
                Object idObj9 = myRes[9];
                String twTotalPriceIn = idObj9.toString();
                Object idObj10 = myRes[10];
                String totalPriceIn = idObj10.toString();
                Object idObj11 = myRes[11];
                String grossProfitIn = idObj11.toString();
                PeriodicReportDetail o = new PeriodicReportDetail(Integer.parseInt(idIn), 
                        dateTimeIn, 
                        foodNameIn, 
                        Integer.parseInt(orderPriceIn), 
                        Integer.parseInt(orderQTYIn), 
                        Integer.parseInt(totalOrderPriceIn), 
                        Integer.parseInt(categoryIn), 
                        Integer.parseInt(takeawayPriceIn), 
                        Integer.parseInt(takeawayQtyIn), 
                        Integer.parseInt(twTotalPriceIn), 
                        Integer.parseInt(totalPriceIn), 
                        Integer.parseInt(grossProfitIn));
                repotData.add(o);
            }
            return repotData;
        }
        catch(Exception ex){
            return repotData;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }        
    }
    
    public List<SummarizeByDate> summarizeByDate(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Database.getPuName());
        EntityManager em = emf.createEntityManager();
        List<SummarizeByDate> repotData = new ArrayList();
        
        try
        {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("summarizeByDate");
            
            List<Object[]> result = spq.getResultList();
            for (int i = 0; i < result.size(); i++){
                Object[] myRes = result.get(i);
                Object idObj0 = myRes[0];
                String dateTimeIn = idObj0.toString();
                Object idObj1 = myRes[1];
                String takeawayPriceIn = idObj1.toString();
                Object idObj2 = myRes[2];
                String qtyIn = idObj2.toString();
                Object idObj3 = myRes[3];
                String orderPriceIn = idObj3.toString();
                Object idObj4 = myRes[4];
                String grossProfitIn = idObj4.toString();
                SummarizeByDate o = new SummarizeByDate(dateTimeIn, 
                        Integer.parseInt(takeawayPriceIn), 
                        Integer.parseInt(qtyIn), 
                        Integer.parseInt(orderPriceIn), 
                        Integer.parseInt(grossProfitIn));
                repotData.add(o);
            }
            return repotData;
        }
        catch(Exception ex){
            return repotData;
        }
        finally{
            em.clear();
            em.close();
            emf.close();
        }        
    }
    
}
