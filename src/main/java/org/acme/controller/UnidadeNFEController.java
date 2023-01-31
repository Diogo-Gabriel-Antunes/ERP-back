package org.acme.controller;

import org.acme.services.NfeService;
import org.acme.services.UnidadeNFEService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("medida")
public class UnidadeNFEController {
    @Inject
    UnidadeNFEService unidadeNFEService;
    @GET
    public Response listAll(){
        return unidadeNFEService.listAll();
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        return unidadeNFEService.listOne(uuid);
    }

    @POST
    public Response create(String json){
        return unidadeNFEService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return unidadeNFEService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response remove(@PathParam("uuid")String uuid){
        return unidadeNFEService.delete(uuid);
    }
}
