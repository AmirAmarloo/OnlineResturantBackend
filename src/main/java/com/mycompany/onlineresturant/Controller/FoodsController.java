/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.Foods;
import com.mycompany.onlineresturant.Service.FoodService;
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
@Path("Foods")
public class FoodsController {

    @Context
    private UriInfo context;
    FoodService fs = new FoodService();
    /**
     * Creates a new instance of FoodsResource
     */
    public FoodsController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.FoodsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FoodsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Path("addFood")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFood(Foods f){
        String rst = fs.addFood(f);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @POST
    @Path("updateFood")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFood(Foods f){
        String rst = fs.updateFood(f);
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @POST
    @Path("deleteFood")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFood(Foods delId){
        String rst = fs.deleteFood(delId.getId());
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }    

    @GET
    @Path("getAllFoods") 
    public Response getAllFoods() {
            List<Foods> foods = fs.getAllFoods();
            JSONArray result = new JSONArray();
            for (Foods food : foods) {
                JSONObject obj = new JSONObject();
                obj.put("id", food.getId());
                obj.put("name", food.getName());
                obj.put("price", food.getPrice());
                obj.put("description", food.getDescription());
                obj.put("category", food.getCategory());
                result.put(obj);
            }
            return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    
}
