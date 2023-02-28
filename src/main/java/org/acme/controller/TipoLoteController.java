package org.acme.controller;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.GsonUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.models.OrdemDeProducao;
import org.acme.models.TipoDeLote;
import org.acme.services.OrdemDeProducaoService;
import org.acme.services.TipoLoteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/tipolote")
@ApplicationScoped
public class TipoLoteController {

    @Inject
    TipoLoteService tipoLoteService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(){
        List<TipoDeLote> tipoLote = TipoDeLote.listAll();
        if(tipoLote.isEmpty()){
            return ResponseBuilder.responseNoContent();
        }
        return ResponseBuilder.responseOk(tipoLote);
    }
    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<TipoDeLote> tipoLote = TipoDeLote.findByIdOptional(uuid);
        if(tipoLote.isPresent()){
            return ResponseBuilder.responseOk(tipoLote.get());
        }
        return ResponseBuilder.responseNoContent();
    }
    @POST
    @Transactional
    public Response create(String json){

        return tipoLoteService.create(json);
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){

        return tipoLoteService.update(uuid,json);
    }

    
    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        TipoDeLote tipoLote = TipoDeLote.findById(uuid);
        if(tipoLote != null){
            TipoDeLote.getEntityManager().remove(tipoLote);
            return ResponseBuilder.responseOk(tipoLote);
        }
        return ResponseBuilder.responseEntityNotFound();
    }
}
