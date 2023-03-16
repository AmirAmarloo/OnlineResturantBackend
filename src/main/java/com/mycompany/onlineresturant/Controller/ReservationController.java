/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.ReservationDef;
import com.mycompany.onlineresturant.Service.ReservationService;
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
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("reservation")
public class ReservationController {

    @Context
    private UriInfo context;
    ReservationService rs = new ReservationService();
    /**
     * Creates a new instance of GenericResource
     */
    public ReservationController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("reservationDef")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder(ReservationDef r){
        String rst = rs.defineReservtion(r);
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }        
    
}
