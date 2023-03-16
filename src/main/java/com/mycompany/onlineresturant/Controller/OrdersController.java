/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.Foods;
import com.mycompany.onlineresturant.Model.OrderByStatus;
import com.mycompany.onlineresturant.Model.OrderQuery;
import com.mycompany.onlineresturant.Model.Orders;
import com.mycompany.onlineresturant.Model.PeriodicReportDetail;
import com.mycompany.onlineresturant.Model.SummarizeByDate;
import com.mycompany.onlineresturant.Service.OrderService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("Orders")
public class OrdersController {

    @Context
    private UriInfo context;
    OrderService os = new OrderService();

    /**
     * Creates a new instance of OrdersController
     */
    public OrdersController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.OrdersController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of OrdersController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @POST
    @Path("addOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(Orders o){
        String rst = os.addOrder(o);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("updateOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder(Orders o){
        String result = os.updateOrder(o);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("deleteOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOrder(int delId){
        String result = os.deleteOrder(delId);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("submitOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitOrder(int userId){
        String rst = os.submitOrder(userId);
        JSONObject obj = new JSONObject();
        obj.put("dateTime", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("getOrders") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOrders(Orders stat) {
        List<OrderQuery> orders = os.getOrders(stat.getStatus());
        JSONArray result = new JSONArray();
        for (OrderQuery order : orders) {
            JSONObject obj = new JSONObject();
            obj.put("foodId", order.getfoodId());
            obj.put("name", order.getName());
            obj.put("price", order.getPrice());
            obj.put("qty", order.getQty());
            obj.put("description", order.getDescription());
            obj.put("category", order.getCategory());
            result.put(obj);
        }
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("changeStatus") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeStatus(Orders stat) {
        String rst = os.changeStatus(stat.getUserId(), stat.getDateTime(), stat.getStatus());
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("getOrdersByStatus") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOrdersByStatus(Orders statusOrder) {
        List<OrderByStatus> orders = os.getOrderByStatus(statusOrder.getStatus());
        JSONArray result = new JSONArray();
        for (OrderByStatus order : orders) {
            JSONObject obj = new JSONObject();
            obj.put("id", order.getId());
            obj.put("name", order.getName());
            obj.put("qty", order.getQty());
            obj.put("description", order.getDescription());
            obj.put("userId", order.getUserId());
            obj.put("dateTime", order.getDateTime());
            obj.put("orderGroup", order.getOrderGroup());
            result.put(obj);
        }
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("periodicReportDetails") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response periodicReportDetails(Orders periodTime) {
        List<PeriodicReportDetail> orders = os.periodicReportDetails(periodTime.getStatus());
        JSONArray result = new JSONArray();
        for (PeriodicReportDetail order : orders) {
            JSONObject obj = new JSONObject();
            obj.put("id", order.getId());
            obj.put("dateTime", order.getDateTime());
            obj.put("foodName", order.getFoodName());
            obj.put("orderPrice", order.getOrderPrice());
            obj.put("orderQTY", order.getOrderQTY());
            obj.put("totalOrderPrice", order.getTotalOrderPrice());
            obj.put("takeawayPrice", order.getTakeawayPrice());
            obj.put("takeawayQty", order.getTakeawayQty());
            obj.put("twTotalPrice", order.getTwTotalPrice());
            obj.put("totalPrice", order.getTotalPrice());
            obj.put("grossProfit", order.getGrossProfit());
            result.put(obj);
        }
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("periodicReportDetailsByDate") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response periodicReportDetailsByDate(Orders periodTime) {
        List<PeriodicReportDetail> orders = os.periodicReportDetailsByDate(periodTime.getDateTime());
        JSONArray result = new JSONArray();
        for (PeriodicReportDetail order : orders) {
            JSONObject obj = new JSONObject();
            obj.put("id", order.getId());
            obj.put("dateTime", order.getDateTime());
            obj.put("foodName", order.getFoodName());
            obj.put("orderPrice", order.getOrderPrice());
            obj.put("orderQTY", order.getOrderQTY());
            obj.put("totalOrderPrice", order.getTotalOrderPrice());
            obj.put("category", order.getCategory());
            obj.put("takeawayPrice", order.getTakeawayPrice());
            obj.put("takeawayQty", order.getTakeawayQty());
            obj.put("twTotalPrice", order.getTwTotalPrice());
            obj.put("totalPrice", order.getTotalPrice());
            obj.put("grossProfit", order.getGrossProfit());
            result.put(obj);
        }
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("summarizeByDate") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response summarizeByDate() {
        List<SummarizeByDate> orders = os.summarizeByDate();
        JSONArray result = new JSONArray();
        for (SummarizeByDate order : orders) {
            JSONObject obj = new JSONObject();
            obj.put("dateTime", order.getDatetime());
            obj.put("takeawayPrice", order.getTakeawayPrice());
            obj.put("QTY", order.getQty());
            obj.put("orderPrice", order.getOrderPrice());
            obj.put("grossProfit", order.getGrossProfit());
            result.put(obj);
        }
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }


}
