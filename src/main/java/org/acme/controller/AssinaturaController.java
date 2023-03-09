package org.acme.controller;

import io.quarkus.panache.common.Sort;
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
        return Assinatura.listAll(Sort.ascending("uuid"));
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
    public Response create(String json) throws Throwable {
        return assinaturaService.create(json);
    }
    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid,String json){


        return assinaturaService.update(uuid,json);
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
