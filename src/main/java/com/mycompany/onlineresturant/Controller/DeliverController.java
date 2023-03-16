/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.Deliver;
import com.mycompany.onlineresturant.Service.DeliverService;
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

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("Deliver")
public class DeliverController {

    @Context
    private UriInfo context;
    DeliverService ds = new DeliverService();
//        IngredientsService ings = new IngredientsService();

    /**
     * Creates a new instance of DeliveController
     */
    public DeliverController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.DeliveController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of DeliveController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }


    @POST
    @Path("addDeliver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeliver(Deliver dlv){
        int uId = dlv.getUserId() ;
        String bywho = dlv.getByWho();
        System.out.println(uId);
        System.out.println(bywho);
        System.out.println("Before Service Call");
        System.out.println(dlv.toString());
        String result = ds.addDeliver(dlv);
        System.out.println("Before Return");
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("updateDeliver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDeliver(Deliver d){
        String result = ds.updateDeliver(d);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
    
    @POST
    @Path("deleteDeliver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDeliver(int delId){
        String result = ds.deleteDeliver(delId);
        return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
    }        
        
}
