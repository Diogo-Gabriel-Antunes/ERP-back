package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.DTO.Financas.ContasAPagarDTO;
import org.acme.services.ContasAPagarService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contasapagar")
@ApplicationScoped
public class ContasAPagarController {
    @Inject
    ContasAPagarService contasAPagarService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ContasAPagar> listAll(){
        return contasAPagarService.listAll();

    }
    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOne(@PathParam("uuid")String uuid){


        return  contasAPagarService.getOne(uuid);
    }
    @POST
    @Transactional
    public Response create(String json) throws Throwable {

        return contasAPagarService.create(json);
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){

        return contasAPagarService.update(uuid,json);
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){

        return contasAPagarService.delete(uuid);
    }
}
