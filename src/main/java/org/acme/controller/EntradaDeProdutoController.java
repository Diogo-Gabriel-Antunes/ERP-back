package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.response.ResponseBuilder;
import org.acme.models.EntradaDeProduto;
import org.acme.services.EntradaDeProdutoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("entradadeproduto")
public class EntradaDeProdutoController {

    @Inject
    EntradaDeProdutoService entradaDeProdutoService;

    @GET
    public Response listAll() {
        List<PanacheEntityBase> entradaDeProduto = EntradaDeProduto.listAll();
        if(entradaDeProduto.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }
        return ResponseBuilder.responseOk(entradaDeProduto);
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<EntradaDeProduto> entradaDeProduto = EntradaDeProduto.findByIdOptional(uuid);
        if(entradaDeProduto.isPresent()){
            return ResponseBuilder.responseOk(entradaDeProduto.get());
        }
        return ResponseBuilder.responseEntityNotFound();
    }

    @POST
    public Response create(String json){
        return entradaDeProdutoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid, String json){

        return entradaDeProdutoService.update(uuid,json);
    }
}


