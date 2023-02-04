package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.cobranca.Assinatura.Boleto.BoletoAsaas;
import org.acme.models.cobranca.Assinatura.Boleto.BoletoAsaasDTO;
import org.acme.services.BoletoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/boleto")
@ApplicationScoped
public class BoletoController {

    @Inject
    BoletoService boletoService;
    private Gson gson = new GsonUtil().parser;
    @GET
    public List<BoletoAsaas> listAll(){
        return boletoService.listAll();
    }
    @GET
    @Path("{uuid}")
    public BoletoAsaas listOne(@PathParam("uuid")String uuid){
        return boletoService.listOne(uuid);
    }
    @GET
    @Path("mes")
    public Response listByMonth(){
        try{
            return Response.ok(boletoService.listByMonth()).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
    @POST
    @Transactional
    public BoletoAsaas create( String json) throws Exception{


        return boletoService.create(json);
    }
    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        BoletoAsaasDTO boletoAsaasDTO = gson.fromJson(json, BoletoAsaasDTO.class);
        BoletoAsaas newBoletoAsaas = boletoService.update(uuid, boletoAsaasDTO);
        return Response.ok(newBoletoAsaas).build();
    }

}
