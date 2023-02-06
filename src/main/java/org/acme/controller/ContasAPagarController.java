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
    public ContasAPagar listOne(@PathParam("uuid")String uuid){
        ContasAPagar contasAPagar = contasAPagarService.getOne(uuid);

        return contasAPagar;
    }
    @POST
    @Transactional
    public ContasAPagar create(String json){
        ContasAPagarDTO contasAPagarDTO = gson.fromJson(json, ContasAPagarDTO.class);
        ContasAPagar contasAPagar = contasAPagarService.create(contasAPagarDTO);
        return contasAPagar;
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public ContasAPagarDTO update(@PathParam("uuid")String uuid, String json){
        ContasAPagarDTO contasAPagarDTO = gson.fromJson(json, ContasAPagarDTO.class);
        contasAPagarService.update(uuid,contasAPagarDTO);
        return contasAPagarDTO;
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        contasAPagarService.delete(uuid);
        return Response.ok(String.format("Conta de id %s deletado com sucesso",uuid)).build();
    }
}
