package org.acme.controller;

import org.acme.models.MateriaPrima;
import org.acme.services.MateriaPrimaService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("materiaprima")
public class MateriaPrimaController {

    @Inject
    MateriaPrimaService materiaPrimaService;
    @GET
    public List<MateriaPrima> listAll(){
        return MateriaPrima.findAll().list();
    }

    @GET
    @Path("{uuid}")
    public MateriaPrima listOne(@PathParam("uuid")String uuid){
        return (MateriaPrima) MateriaPrima.findByIdOptional(uuid).get();
    }

    @POST
    @Transactional
    public Response create(String json){
        return materiaPrimaService.create(json);

    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid,String json){
        return materiaPrimaService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return materiaPrimaService.delete(uuid);
    }
}
