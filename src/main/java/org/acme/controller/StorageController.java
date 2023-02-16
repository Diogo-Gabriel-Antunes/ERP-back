package org.acme.controller;


import org.acme.models.Estoque;
import org.acme.services.EstoqueService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/storage")
public class StorageController {

    @Inject
    EstoqueService estoqueService;




    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estoque> listAll(){
        return estoqueService.findAll();
    }

    @GET
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Estoque listOne(@PathParam("uuid") String uuid){
        return estoqueService.findOne(uuid);
    }

    @GET
    @Path("/mes")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estoque> listAMonth(){
        return estoqueService.findMonth();
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("uuid")String uuid,String json){

        return estoqueService.update(uuid, json);
    }

}