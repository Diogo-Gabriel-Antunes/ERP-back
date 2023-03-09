package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.Devolucao;
import org.acme.services.DevolucaoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("devolucao")
public class DevolucaoController {
    @Inject
    private DevolucaoService devolucaoService;
    @GET
    public Response listAll(){
        List<Devolucao> devolucoes = Devolucao.listAll();
        if(devolucoes.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }else{
            return ResponseBuilder.responseOk(devolucoes);
        }

    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<Devolucao> devolucao = Devolucao.findByIdOptional(uuid);
        if(devolucao.isPresent()){
            return ResponseBuilder.responseOk(devolucao);
        }else{
            return ResponseBuilder.responseNoContent();
        }

    }
    @POST
    public Response create(String json){
        return devolucaoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid, String json){
        return devolucaoService.update(uuid,json);
    }
}
