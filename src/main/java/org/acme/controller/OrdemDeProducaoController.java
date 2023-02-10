package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.OrdemDeProducaoDTO;
import org.acme.models.OrdemDeProducao;
import org.acme.services.OrdemDeProducaoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ordemdeproducao")
@ApplicationScoped
public class OrdemDeProducaoController {
    @Inject
    OrdemDeProducaoService ordemDeProducaoService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrdemDeProducao> listAll(){
        return ordemDeProducaoService.findAll();

    }
    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrdemDeProducao listOne(@PathParam("uuid")String uuid){
        return ordemDeProducaoService.findOne(uuid);
    }
    @POST
    @Transactional
    public Response create(String json){

        return ordemDeProducaoService.create(json);
    }
    @GET
    @Path("/mes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrdemDeProducao> listByMonth(){
        return ordemDeProducaoService.findByMonth();
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){

        return ordemDeProducaoService.update(uuid,json);
    }
    @PUT
    @Path("{uuid}/finalizar")
    @Transactional
    public Response finish(@PathParam("uuid") String uuid ){
        try{
            ordemDeProducaoService.updateFinish(uuid);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    
    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        ordemDeProducaoService.delete(uuid);
        return Response.ok(String.format("Conta de id %s deletado com sucesso",uuid)).build();
    }
}
