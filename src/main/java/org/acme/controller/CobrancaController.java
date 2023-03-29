package org.acme.controller;

import com.google.gson.Gson;
import org.acme.models.asaas.CobrancaParcelada;
import org.acme.models.asaas.CobrancaParceladaRetorno;
import org.acme.services.CobrancaRetornoService;
import org.acme.services.CobrancaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/cobranca")
public class CobrancaController {

    @Inject
    CobrancaService cobrancaService;
    @Inject
    CobrancaRetornoService cobrancaRetornoService;
    @GET
    public List<CobrancaParcelada> listAll(){
        return cobrancaService.getAll();
    }
    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        return cobrancaService.listOne(uuid);
    }

    @POST
    @Transactional
    public Response create(String json){
        return cobrancaService.create(json);
    }
    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid") String uuid, String json){
        return cobrancaService.update(uuid,json);
    }
    @GET
    @Path("retorno")
    public List<CobrancaParceladaRetorno> listAllRetorno(){
        return cobrancaRetornoService.getAll();
    }
}
