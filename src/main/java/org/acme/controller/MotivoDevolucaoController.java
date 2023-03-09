package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.MotivoDaDevolucao;
import org.acme.services.MotivoDevolucaoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("motivodevolucao")
public class MotivoDevolucaoController {
    @Inject
    private MotivoDevolucaoService motivoDevolucaoService;
    @GET
    public Response listAll(){
        List<MotivoDaDevolucao> motivos = MotivoDaDevolucao.listAll();
        if(motivos.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }else{
            return ResponseBuilder.responseOk(motivos);
        }

    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<MotivoDaDevolucao> devolucao = MotivoDaDevolucao.findByIdOptional(uuid);
        if(devolucao.isPresent()){
            return ResponseBuilder.responseOk(devolucao);
        }else{
            return ResponseBuilder.responseNoContent();
        }

    }
    @POST
    public Response create(String json){
        return motivoDevolucaoService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response create(@PathParam("uuid") String uuid,String json){
        return motivoDevolucaoService.update(uuid,json);
    }
}
