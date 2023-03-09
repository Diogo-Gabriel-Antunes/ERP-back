package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.EstoqueExterno;
import org.acme.services.EstoqueExternoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("estoqueexterno")
public class EstoqueExternoController {

    @Inject
    EstoqueExternoService estoqueExternoService;

    @GET
    public Response listAll(){
        List<EstoqueExterno> estoqueExterno = EstoqueExterno.listAll();
        if(estoqueExterno.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }else{
            return ResponseBuilder.responseOk(estoqueExterno);
        }
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<EstoqueExterno> estoqueExterno = EstoqueExterno.findByIdOptional(uuid);
        if(estoqueExterno.isPresent()){
            return ResponseBuilder.responseOk(estoqueExterno.get());
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }
    }

    @POST
    public Response create (String json){
        return estoqueExternoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json){
        return estoqueExternoService.update(uuid,json);
    }
    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid") String uuid){
        Optional<EstoqueExterno> estoqueExterno = EstoqueExterno.findByIdOptional(uuid);
        if(estoqueExterno.isPresent()){
            EstoqueExterno.getEntityManager().remove(estoqueExterno);
            return ResponseBuilder.responseOk(estoqueExterno);
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }

    }

}

