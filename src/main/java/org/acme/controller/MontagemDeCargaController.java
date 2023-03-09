package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.MontagemDeCarga;
import org.acme.services.MontagemDeCargaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("montagemdecarga")
@ApplicationScoped
public class MontagemDeCargaController {

    @Inject
    MontagemDeCargaService montagemDeCargaService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(){
        return montagemDeCargaService.listAll();
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<MontagemDeCarga> montagemDeCarga = MontagemDeCarga.findByIdOptional(uuid);
        if(montagemDeCarga.isPresent()){
            return ResponseBuilder.responseOk(montagemDeCarga.get());
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }
    }
    @POST
    @Transactional
    public Response create(String json){
        return montagemDeCargaService.create(json);
    }
    @POST
    @Path("preparacao")
    public Response createPreparacaoDeCarga(String json){
        return montagemDeCargaService.criarPreparacaoDeCarga(json);
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){
        return montagemDeCargaService.update(uuid,json);
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return montagemDeCargaService.delete(uuid);
    }


}
