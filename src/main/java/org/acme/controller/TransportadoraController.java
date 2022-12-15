package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.TransportadoraDTO;
import org.acme.models.Endereco;
import org.acme.models.Transportadora;
import org.acme.services.TransportadoraService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.List;

@Path("transportadora")
@ApplicationScoped
public class TransportadoraController {

    @Inject
    TransportadoraService transportadoraService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transportadora> listAll(){
        return transportadoraService.findAll();
//        Transportadora transportadora = new Transportadora();
//        transportadora.setIe("123123123");
//        transportadora.setNome("Teste");
//        transportadora.setCnpj("11111111000111");
//        Endereco endereco = new Endereco();
//        endereco.setXEnder("Teste");
//        endereco.setUf("sc");
//        endereco.setXMun("sc");
//        endereco.setCEP("11111111");
//        transportadora.setEndereco(endereco);
//        return List.of(transportadora);
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public TransportadoraDTO listOne(@PathParam("uuid")String uuid){
        Transportadora transportadora = transportadoraService.findOne(uuid);

        return TransportadoraDTO.convert(transportadora);
    }
    @POST
    @Transactional
    public TransportadoraDTO create(String json){
        TransportadoraDTO transportadoraDTO = gson.fromJson(json, TransportadoraDTO.class);
        transportadoraService.create(transportadoraDTO);
        return transportadoraDTO;
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public TransportadoraDTO update(@PathParam("uuid")String uuid, String json){
        TransportadoraDTO transportadoraDTO = gson.fromJson(json, TransportadoraDTO.class);
        transportadoraService.update(uuid,transportadoraDTO);
        return transportadoraDTO;
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return transportadoraService.delete(uuid);
    }
}
