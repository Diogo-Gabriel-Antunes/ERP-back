package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.response.ResponseBuilder;
import org.acme.models.Fornecedor;
import org.acme.services.FornecedorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("fornecedor")
public class FornecedorController {

    @Inject
    FornecedorService fornecedorService;
    @GET
    public Response listAll(){
        List<PanacheEntityBase> fornecedor = Fornecedor.listAll();
        if(fornecedor.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }
        return ResponseBuilder.responseOk(fornecedor);
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<Fornecedor> fornecedor = Fornecedor.findByIdOptional(uuid);
        if(fornecedor.isPresent()){
            return ResponseBuilder.responseOk(fornecedor.get());
        }
        return ResponseBuilder.responseNoContent();
    }
    @POST
    public Response create(String json){
        return fornecedorService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return fornecedorService.update(uuid,json);
    }
}
