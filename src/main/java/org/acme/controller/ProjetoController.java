package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.ProjetoDTO;
import org.acme.models.Projeto;
import org.acme.services.ProjetoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/projeto")
@ApplicationScoped
public class ProjetoController {

    @Inject
    ProjetoService projetoService;
    @GET
    public List<Projeto> listAll(){
        return Projeto.listAll();
    }
    @GET
    @Path("{uuid}")
    public Projeto listOne(@PathParam("uuid")String uuid){
        return Projeto.findById(uuid);
    }

    @POST
    @Transactional
    public Response create(String json){

        return projetoService.create(json);
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid") String uuid,String json){
        return projetoService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        Projeto.deleteById(uuid);
        return Response.ok().build();
    }
}
