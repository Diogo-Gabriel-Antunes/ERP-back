package org.acme.controller;

import org.acme.models.Loja;
import org.acme.services.LojaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("loja")
public class LojaController {

    @Inject
    private LojaService lojaService;

    @GET
    public List<Loja> listAll() {
        return Loja.listAll();
    }

    @GET
    @Path("{uuid}")
    public Loja listOne(@PathParam("uuid") String uuid) {
        return (Loja) Loja.findByIdOptional(uuid).get();
    }

    @POST
    @Transactional
    public Response create(String json) {
        Loja loja = lojaService.create(json);
        if(loja != null){
            return Response.ok(loja).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid") String uuid, String json) {
        Loja loja = lojaService.update(uuid, json);
        if (loja != null) {
            return Response.ok(loja).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response delete(@PathParam("uuid") String uuid){
        return lojaService.delete(uuid);
    }
}
