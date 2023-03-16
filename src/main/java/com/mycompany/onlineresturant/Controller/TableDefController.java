package com.mycompany.onlineresturant.Controller;

import com.mycompany.onlineresturant.Model.TableDef;
import com.mycompany.onlineresturant.Service.TableDefService;
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
@Path("tableDef")
public class TableDefController {

    @Context
    private UriInfo context;
    TableDefService tds = new TableDefService();

    /**
     * Creates a new instance of TableDefController
     */
    public TableDefController() {
    }

    /**
     * Retrieves representation of an instance of com.mycompany.onlineresturant.Controller.TableDefController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TableDefController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Path("defineTable")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response defineTabele(TableDef td){
        String rst = tds.defineTable(td);
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Path("deleteTable")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTable(TableDef delId){
        String rst = tds.deleteTable(delId.getId());
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @POST
    @Path("updateTable")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTable(TableDef t){
        String rst = tds.updateTable(t);
        JSONObject obj = new JSONObject();
        obj.put("result", rst);
        return Response.status(Response.Status.OK).entity(obj.toString()).type(MediaType.APPLICATION_JSON).build();
    }    
    
    @GET
    @Path("getAllTables") 
    public Response getAllTables() {
            List<TableDef> tables = tds.getAllTables();
            JSONArray result = new JSONArray();
            for (TableDef table : tables) {
                JSONObject obj = new JSONObject();
                obj.put("id", table.getId());
                obj.put("tableNo", table.getTableNo());
                obj.put("qty", table.getQty());
                result.put(obj);
            }
            return Response.status(Response.Status.OK).entity(result.toString()).type(MediaType.APPLICATION_JSON).build();
    }
            
    
}
