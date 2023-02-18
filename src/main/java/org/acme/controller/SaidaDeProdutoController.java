package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.exceptions.ResponseBuilder;
import org.acme.models.DTO.SaidaDeProdutoDTO;
import org.acme.models.SaidaDeProduto;
import org.acme.services.SaidaDeProdutoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("saidadeproduto")
public class SaidaDeProdutoController {

    @Inject
    SaidaDeProdutoService saidaDeProdutoService;
    @GET
    public List<SaidaDeProduto> listAll(){
        return SaidaDeProduto.listAll();
    }
    @GET
    @Path("{uuid}")
    public SaidaDeProduto listOne(@PathParam("uuid")String uuid){
        return SaidaDeProduto.findById(uuid);
    }
    @POST
    public Response create(String json){
        return saidaDeProdutoService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json){
        return saidaDeProdutoService.update(uuid,json);
    }

    @DELETE
    @Transactional
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        Optional<SaidaDeProduto> saidaDeProduto = SaidaDeProduto.findByIdOptional(uuid);
        if(saidaDeProduto.isPresent()){
            SaidaDeProduto.deleteById(uuid);
            return ResponseBuilder.responseOk(saidaDeProduto.get());
        }
        return ResponseBuilder.responseEntityNotFound();
    }
}
