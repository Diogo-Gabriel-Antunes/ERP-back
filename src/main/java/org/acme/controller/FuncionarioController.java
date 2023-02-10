package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.FuncionarioDTO;
import org.acme.models.Funcionario;
import org.acme.services.FuncionarioService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("funcionario")
public class FuncionarioController {

    @Inject
    FuncionarioService funcionarioService;
    private Gson gson = new GsonUtil().parser;

    @GET
    public List<Funcionario> listAll(){
        return Funcionario.listAll();
    }
    @GET
    @Path("{uuid}")
    public Funcionario listOne(@PathParam("uuid")String uuid){
        return Funcionario.findById(uuid);
    }
    @POST
    @Transactional
    public Response create(String json){

        return funcionarioService.create(json);

    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid") String uuid,String json){
        return funcionarioService.update(uuid,json);
    }
    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response delete(@PathParam("uuid") String uuid,String json){
        return funcionarioService.delete(uuid);
    }
}
