package org.acme.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acme.Util.GsonUtil;
import org.acme.Util.LocalDateAdapter;
import org.acme.models.DTO.RequestDTO;
import org.acme.models.Request;
import org.acme.services.RequestService;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/request")
public class RequestController {

    @Inject
    RequestService requestService;

    private Gson gson = new GsonUtil().parser;

    @GET
    public List<Request> listAll(){
        return requestService.findAll();
    }
    @GET
    @Path("{uuid}")
    public Request getOne(@PathParam("uuid")String uuid){
        return requestService.findOne(uuid);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(String json){
        RequestDTO requestDTO = gson.fromJson(json,RequestDTO.class);
        requestService.create(requestDTO);
        return Response.ok().build();
    }

    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String newObject ){
        RequestDTO requestDTO = gson.fromJson(newObject, RequestDTO.class);
        requestService.update(uuid,requestDTO);
        return Response.ok().build();
    }
}
