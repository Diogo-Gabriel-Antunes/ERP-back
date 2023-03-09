package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.response.ResponseBuilder;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.services.TransportadoraService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

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
    public Response listOne(@PathParam("uuid")String uuid){
        Optional<Transportador> transportador = Transportador.findByIdOptional(uuid);
        if(transportador.isPresent()){
            return ResponseBuilder.responseOk(transportador.get());
        }else{
            return ResponseBuilder.responseEntityNotFound();
        }
    }
    @POST
    @Transactional
    public Response create(String json){
        return transportadoraService.create(json);
    }
    @PUT
    @Path("{uuid}")
    @Transactional
    public Response update(@PathParam("uuid")String uuid, String json){
        return transportadoraService.update(uuid,json);
    }

    @Path("{uuid}")
    @DELETE
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){
        return transportadoraService.delete(uuid);
    }
}
