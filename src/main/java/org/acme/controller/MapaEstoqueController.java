package org.acme.controller;

import org.acme.response.ResponseBuilder;
import org.acme.models.MapaEstoque;
import org.acme.services.MapaEstoqueService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/mapaestoque")
public class MapaEstoqueController {

    @Inject
    MapaEstoqueService mapaEstoqueService;
    @GET
    public List<MapaEstoque> listAll(){
        return MapaEstoque.listAll();
    }

    @GET
    @Path("{uuid}")
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<MapaEstoque> mapaEstoque = MapaEstoque.findByIdOptional(uuid);
        if(mapaEstoque.isPresent()){
            return ResponseBuilder.responseOk(mapaEstoque.get());
        }
        return ResponseBuilder.responseEntityNotFound();
    }

    @POST
    public Response create(String json){
        return mapaEstoqueService.create(json);
    }

    @PUT
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return mapaEstoqueService.update(uuid,json);
    }
}
