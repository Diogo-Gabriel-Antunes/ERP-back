package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.TransportadorDTO;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.services.TransportadoraService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transportador")
@ApplicationScoped
public class TransportadorController {

    @Inject
    TransportadoraService transportadoraService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transportador> listAll(){
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
    public TransportadorDTO listOne(@PathParam("uuid")String uuid){
        Transportador transportador = transportadoraService.findOne(uuid);

        return TransportadorDTO.convert(transportador);
    }
    @POST
    @Transactional
    public TransportadorDTO create(String json){
        TransportadorDTO transportadorDTO = gson.fromJson(json, TransportadorDTO.class);
        transportadoraService.create(transportadorDTO);
        return transportadorDTO;
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public TransportadorDTO update(@PathParam("uuid")String uuid, String json){
        TransportadorDTO transportadoraDTO = gson.fromJson(json, TransportadorDTO.class);
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
