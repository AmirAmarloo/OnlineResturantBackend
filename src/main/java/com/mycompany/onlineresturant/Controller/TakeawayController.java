/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.Takeaway;
import com.mycompany.onlineresturant.Service.TakeawayService;
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
@Path("Takeaway")
public class TakeawayController {

    @Context
    private UriInfo context;
    TakeawayService ts = new TakeawayService();

    /**
     * Creates a new instance of TakeawayController
     */
    public TakeawayController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.TakeawayController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TakeawayController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("addTakeaway")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTakeaway(Takeaway t){
        String rst = ts.addTakeaway(t);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("updateTakeaway")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTakeaway(Takeaway t){
        String result = ts.updateTakeaway(t);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("deleteTakeaway")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTakeaway(int delId){
        String result = ts.deleteTakeaway(delId);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
     
    
}
