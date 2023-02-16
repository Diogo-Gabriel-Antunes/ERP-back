package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Pedido;
import org.acme.services.PedidoService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/request")
public class RequestController {

    @Inject
    PedidoService pedidoService;



    @GET
    public List<Pedido> listAll(){
        return pedidoService.findAll();
    }
    @GET
    @Path("{uuid}")
    public Pedido getOne(@PathParam("uuid")String uuid){
        return pedidoService.findOne(uuid);
    }

    @GET
    @Path("mes")
    public List<Pedido> listAllAMonth(){
        return pedidoService.findMonth();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(String json){

        return pedidoService.create(json);
    }

    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json ){

        return pedidoService.update(uuid, json);
    }
    @PUT
    @Transactional
    @Path("{uuid}/finalizar")
    public Response updateFinish(@PathParam("uuid")String uuid){
        return pedidoService.updateFinish(uuid);
    }
}
