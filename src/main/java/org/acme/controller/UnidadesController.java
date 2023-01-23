package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.UnidadeDTO;
import org.acme.models.Unidade;
import org.acme.services.UnidadesService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/unidades")
public class UnidadesController {
    @Inject
    UnidadesService unidadesService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidade> listAll(){
        return unidadesService.findAll();

    }

    @POST
    @Transactional
    public UnidadeDTO create(String json){
        UnidadeDTO unidadeDTO = gson.fromJson(json, UnidadeDTO.class);
        unidadesService.create(unidadeDTO);
        return unidadeDTO;
    }
    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public UnidadeDTO listOne(@PathParam("uuid")String uuid){
        Unidade unidade = unidadesService.findOne(uuid);

        return UnidadeDTO.convert(unidade);
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public UnidadeDTO update(@PathParam("uuid")String uuid, String json){
        UnidadeDTO unidadeDTO = gson.fromJson(json, UnidadeDTO.class);
        unidadesService.update(uuid,unidadeDTO);
        return unidadeDTO;
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return unidadesService.delete(uuid);
    }
}
