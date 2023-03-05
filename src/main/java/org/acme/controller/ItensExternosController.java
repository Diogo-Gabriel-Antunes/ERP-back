package org.acme.controller;


import org.acme.models.ItensExternos;
import org.acme.services.ItensExternosService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/itensexternos")
public class ItensExternosController {

    @Inject
    ItensExternosService itensExternosService;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItensExternos> listAll(){
        return itensExternosService.findAll();
    }

    @GET
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public ItensExternos listOne(@PathParam("uuid") String uuid){
        return itensExternosService.findOne(uuid);
    }

    @POST
    public Response create(String json){
        return itensExternosService.create(json);
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("uuid")String uuid,String json){

        return itensExternosService.update(uuid, json);
    }

}
