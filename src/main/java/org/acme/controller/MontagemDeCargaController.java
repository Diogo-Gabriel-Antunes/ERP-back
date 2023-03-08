package org.acme.controller;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.ArrayUtil;
import org.acme.Util.GsonUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.models.MontagemDeCarga;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.services.MontagemDeCargaService;
import org.acme.services.TransportadoraService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("montagemdecarga")
@ApplicationScoped
public class MontagemDeCargaController {

    @Inject
    MontagemDeCargaService montagemDeCargaService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(){
        return montagemDeCargaService.listAll();
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<MontagemDeCarga> montagemDeCarga = MontagemDeCarga.findByIdOptional(uuid);
        if(montagemDeCarga.isPresent()){
            return ResponseBuilder.responseOk(montagemDeCarga.get());
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }
    }
    @POST
    @Transactional
    public Response create(String json){
        return montagemDeCargaService.create(json);
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){
        return montagemDeCargaService.update(uuid,json);
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return montagemDeCargaService.delete(uuid);
    }
}
