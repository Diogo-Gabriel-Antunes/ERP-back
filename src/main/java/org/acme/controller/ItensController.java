package org.acme.controller;


import com.google.gson.Gson;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.Itens;
import org.acme.services.ItensService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/itens")
public class ItensController {

    @Inject
    ItensService itensService;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Itens> listAll(){
        return itensService.findAll();
    }

    @GET
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Itens listOne(@PathParam("uuid") String uuid){
        return itensService.findOne(uuid);
    }

    @GET
    @Path("/mes")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Itens> listAMonth(){
        return itensService.findMonth();
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("uuid")String uuid,String newStorage){
        return Response.ok("").build();
    }

}
