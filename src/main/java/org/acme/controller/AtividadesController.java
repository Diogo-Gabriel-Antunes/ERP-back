package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Atividade;
import org.acme.services.AtividadeService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("atividades")
public class AtividadesController {
    private Gson gson = new GsonUtil().parser;
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
    public Atividade create(String json) throws Exception{
        Atividade atividade = gson.fromJson(json, Atividade.class);
        Atividade.persist(atividade);
        return atividade;
    }
    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid, String json){
        return atividadeService.update(uuid,json);
    }
}
