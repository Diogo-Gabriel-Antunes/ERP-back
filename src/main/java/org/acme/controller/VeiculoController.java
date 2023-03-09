package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.services.VeiculoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("veiculo")
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;
    @GET
    public Response listAll(){
        return veiculoService.listAll();
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<Veiculo> veiculo = Veiculo.findByIdOptional(uuid);
        if(veiculo.isPresent()){
            return ResponseBuilder.responseOk(veiculo.get());
        }else {
            return ResponseBuilder.responseEntityNotFound();
        }
    }

    @POST
    public Response create(String json){
        return veiculoService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return veiculoService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        Optional<Veiculo> veiculo = Veiculo.findByIdOptional(uuid);
        if(veiculo.isPresent()){
            veiculo.get().delete();
            return ResponseBuilder.responseOk(veiculo.get());
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }
    }
}
