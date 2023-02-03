package org.acme.controller;

import org.acme.services.ImportacaoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("importacao")
public class ImportacaoController {

    @Inject
    ImportacaoService importacaoService;

    @GET
    public Response listAll() {
        return importacaoService.listAll();
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid) {
        return importacaoService.listOne(uuid);
    }

    @POST
    public Response create(String json) {
        return importacaoService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json) {
        return importacaoService.update(uuid,json);
    }
    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        return importacaoService.delete(uuid);
    }
}
