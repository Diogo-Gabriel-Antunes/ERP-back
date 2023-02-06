package org.acme.controller;

import org.acme.models.asaas.Assinatura.Assinatura;
import org.acme.services.AssinaturaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("assinatura")
public class AssinaturaController {

    @Inject
    AssinaturaService assinaturaService;
    @GET
    public List<Assinatura> listAll(){
        return Assinatura.listAll();
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<Assinatura> assinatura = Assinatura.findByIdOptional(uuid);
        if(assinatura.isPresent()){
            return Response.ok(assinatura).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    public Response create(String json){
        return assinaturaService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json){
        Assinatura assinatura = assinaturaService.update(uuid,json);
        if(assinatura == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(assinatura).build();
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        Optional<Assinatura> assinatura = Assinatura.findByIdOptional(uuid);
        if(assinatura.isPresent()){
            assinatura.get().delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
