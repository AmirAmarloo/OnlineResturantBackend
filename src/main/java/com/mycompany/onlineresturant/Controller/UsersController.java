/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Configuration.JwtToken;
import com.mycompany.onlineresturant.Model.Users;
import com.mycompany.onlineresturant.Service.UsersService;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("Users")
public class UsersController {

    @Context
    private UriInfo context;
    UsersService us = new UsersService();

    /**
     * Creates a new instance of UsersController
     */
    public UsersController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.UsersController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UsersController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    
    @GET
    @Path("getAllUsers") 
    public Response getAllMOvie() {
            List<Users> urses = us.getAllUsers();
            JSONArray result = new JSONArray();
            for (Users user : urses) {
                JSONObject obj = new JSONObject();
                obj.put("id", user.getId());
                obj.put("name", user.getName());
                obj.put("family", user.getFamily());
                obj.put("email", user.getEmail());
                obj.put("address", user.getAddress());
                obj.put("phone", user.getPhone());
                obj.put("token", user.getToken());
                obj.put("status", user.getStatus());
                obj.put("category", user.getCategory());
                result.put(obj);
            }
//            Response.ok().header("Access-Control-Allow-Origin", "*");
//            Response.ok().header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
//            Response.ok().header("Access-Control-Max-Age", "8080");
//            Response.ok().header("Access-Control-Allow-Credentials", "true");
//            Response.ok().header("Access-Control-Allow-Headers", "x-requested-with,Access-Control-Allow-Origin,CopyStreamException,Access-Control-Allow-Methods,Access-Control-Max-Age");
//            Response.ok().build();
            

//            return Response.status(Response.Status.OK).entity(result.toString()).header("Access-Control-Allow-Origin", "*").build();
            return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    
@POST
@Path("addNewUser")
@Consumes(MediaType.APPLICATION_JSON)
public Response addNewUser(Users user){
    String rst = us.addUser(user);

    JSONArray result = new JSONArray();
    JSONObject obj = new JSONObject();
    obj.put("result", rst);
    result.put(obj);
    return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
}
    
@GET
@Path("ActivateUser")
public Response activateAcount(@QueryParam("t") String token){
    System.err.println("Confirm started");
    String result = us.activateAcount(token);
    return Response.status(Response.Status.OK).entity(result).type(MediaType.APPLICATION_JSON).build();
}
    
    
@POST
@Path("loginUser")
@Consumes(MediaType.APPLICATION_JSON)
public Response loginUser(Users u) {
        Users user = us.loginUser(u.getEmail(), u.getPassword());
        JSONObject obj = new JSONObject();
        obj.put("id", user.getId());
        obj.put("name", user.getName());
        obj.put("family", user.getFamily());
        obj.put("email", user.getEmail());
        obj.put("address", user.getAddress());
        obj.put("phone", user.getPhone());
        obj.put("token", user.getToken());
        obj.put("status", user.getStatus());
        obj.put("category", user.getCategory());        
        System.out.println("Ligin user Called:" + user.getFamily());
        System.out.println("Ligin user Called:" + user.toString());
        if (user.getFamily() != null){
            String token = JwtToken.createJwt(user);
            obj.put("token", token);
        }
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
}

@POST
@Path("resetPassword")
@Consumes(MediaType.APPLICATION_JSON)
public Response resetPassword(Users u){
    String email = u.getEmail();
    String result = us.resetPassword(email);
    JSONObject obj = new JSONObject();
    obj.put("result", result);
    return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
} 

    @POST
    @Path("resetPasswordLink")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPasswordLink(Users u){
        String email = u.getEmail();
        String password = u.getPassword();
        String pwtoken = u.getToken();
        System.err.println(pwtoken);
        
        String result = us.resetPasswordLink(email, password, pwtoken);
        JSONObject obj = new JSONObject();
        obj.put("result", result);
        
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    } 

    @POST
    @Path("updateUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFood(Users u){
        String rst = us.updateUser(u);
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @POST
    @Path("deleteUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFood(Users delId){
        String rst = us.deleteUser(delId.getId());
        JSONArray result = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        result.put(obj);
        return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
}


