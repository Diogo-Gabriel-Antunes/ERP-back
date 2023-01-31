package org.acme.controller;

import org.acme.models.NFE.NFE;
import org.acme.services.NfeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("nfe")
public class NFEController {

    @Inject
    NfeService nfeService;
    @GET
    public Response listAll(){
        return nfeService.listAll();
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        return nfeService.listOne(uuid);
    }

    @POST
    public Response create(String json){
        return nfeService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return nfeService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response remove(@PathParam("uuid")String uuid){
        return nfeService.delete(uuid);
    }
}
