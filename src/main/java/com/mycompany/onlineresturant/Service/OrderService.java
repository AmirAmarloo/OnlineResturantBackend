
package com.mycompany.onlineresturant.Service;

import com.mycompany.onlineresturant.Model.OrderByStatus;
import com.mycompany.onlineresturant.Model.OrderQuery;
import com.mycompany.onlineresturant.Model.Orders;
import com.mycompany.onlineresturant.Model.PeriodicReportDetail;
import com.mycompany.onlineresturant.Model.SummarizeByDate;
import java.util.List;

public class OrderService {
    Orders o = new Orders();
    
    public String addOrder(Orders ord){
        if (ord.getQty() < 1){
            return "Quantity is not acceptable!";
        }
        return o.addOrders(ord);
    }
    
    public String updateOrder(Orders ord){
        try
        {
            return o.updateOrders(ord);
        }
        catch(Exception ex)
        {
            return ex.getMessage();
        }
    }
    
    public String deleteOrder(int delId){
        try
        {
            return o.deleteOrder(delId);
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public List<OrderQuery> getOrders(int stat){
        return o.getOrders(stat);
    }
    
    public String submitOrder(int userId){
        return o.submitOrder(userId);
    }
    
    public String changeStatus(int userId, String datetimeOrder, int statusOrder){
        return o.changeStatus(userId, datetimeOrder, statusOrder);
    }
    
    public List<OrderByStatus> getOrderByStatus(int statusOrder){
        return o.getOrderByStatus(statusOrder);
    }
    
    public List<PeriodicReportDetail> periodicReportDetails(int periodTime){
        return o.periodicReportDetails(periodTime);
    }
    
    public List<PeriodicReportDetail> periodicReportDetailsByDate(String dateIn){
        return o.periodicReportDetailsByDate(dateIn);
    }
    
    public List<SummarizeByDate> summarizeByDate(){
        return o.summarizeByDate();
    }
}
