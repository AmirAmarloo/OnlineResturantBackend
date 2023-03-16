/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.TakeawayStuff;
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
@Path("TakeawayStuff")
public class TakeawayStuffController {

    @Context
    private UriInfo context;
    TakeawayStuff tss = new TakeawayStuff();

    /**
     * Creates a new instance of TakeawayStuffController
     */
    public TakeawayStuffController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.TakeawayStuffController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TakeawayStuffController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("addTakeawayStuff")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTakeawayStuff(TakeawayStuff t){
        String res = tss.addTakeawayStuff(t);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", res);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("updateTakeawayStuff")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTakeawayStuff(TakeawayStuff t){
        String res = tss.updateTakeawayStuff(t);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", res);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("deleteTakeawayStuff")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTakeawayStuff(TakeawayStuff delId){
        String res = tss.deleteTakeawayStuff(delId.getId());
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", res);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @GET
    @Path("getAllTakeawayStuff") 
    public Response getAllTakeawayStuff() {
            List<TakeawayStuff> tks = tss.getAllTakeawayStuff();
            JSONArray result = new JSONArray();
            for (TakeawayStuff tk : tks) {
                JSONObject obj = new JSONObject();
                obj.put("id", tk.getId());
                obj.put("description", tk.getDescription());
                obj.put("price", tk.getPrice());
                result.put(obj);
            }
            return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
}
