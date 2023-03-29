package org.acme.controller;

import org.acme.models.Funcionario;
import org.acme.services.FuncionarioService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("funcionario")
public class FuncionarioController {

    @Inject
    FuncionarioService funcionarioService;

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
