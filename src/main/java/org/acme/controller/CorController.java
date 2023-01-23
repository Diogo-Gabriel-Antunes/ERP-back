package org.acme.controller;

import org.acme.models.Nota_fiscal_eletronica.Cor;
import org.acme.services.CorService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cor")
public class CorController {

    @Inject
    CorService corService;
    @GET
    public Response listAll(@QueryParam("descricao") String descricao) {
        if(descricao != null){
           return corService.findByDescricao(descricao);
        }
        return Response.ok(Cor.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Cor listOne(@PathParam("id") String id) {
        return Cor.findById(id);
    }


    @POST
    @Path("setupcores")
    @Transactional
    public Response setupCores() {
        return corService.setupCores();
    }
}
