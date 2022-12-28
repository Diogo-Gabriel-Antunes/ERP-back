package org.acme.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acme.Util.GsonUtil;
import org.acme.Util.LocalDateAdapter;
import org.acme.models.DTO.StorageDTO;
import org.acme.models.Storage;
import org.acme.services.StorageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Path("/storage")
public class StorageController {

    @Inject
    StorageService storageService;

    private Gson gson = new GsonUtil().parser;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Storage> listAll(){
        return storageService.findAll();
    }

    @GET
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Storage listOne(@PathParam("uuid") String uuid){
        return storageService.findOne(uuid);
    }

    @GET
    @Path("/mes")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Storage> listAMonth(){
        return storageService.findMonth();
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("uuid")String uuid,String newStorage){
        StorageDTO storageDTO = gson.fromJson(newStorage, StorageDTO.class);
        Storage update = storageService.update(uuid, storageDTO);
        return Response.ok(update).build();
    }

}
