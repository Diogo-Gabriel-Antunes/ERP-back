package org.acme.controller;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.GsonUtil;
import org.acme.models.Atividade;
import org.acme.services.AtividadeService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("atividade")
public class AtividadesController {
    @Inject
    AtividadeService atividadeService;
    @GET
    public List<Atividade> listAll(){
        return Atividade.listAll();
    }
    @GET
    @Path("{uuid}")
    public Atividade listOne(@PathParam("uuid")String uuid){
        return Atividade.findById(uuid);
    }

    @POST
    @Transactional
    public Response create(String json) {
        return atividadeService.create(json);
    }
    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid, String json){
        return atividadeService.update(uuid,json);
    }

    @DELETE
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        if(Atividade.deleteById(uuid)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
