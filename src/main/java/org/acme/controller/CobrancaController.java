package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.asaas.CobrancaParcelada;
import org.acme.models.asaas.CobrancaParceladaDTO;
import org.acme.services.CobrancaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@ApplicationScoped
@Path("/cobranca")
public class CobrancaController {

    private Gson gson = new GsonUtil().parser;
    @Inject
    CobrancaService cobrancaService;
    @GET
    public List<CobrancaParcelada> listAll(){
        return cobrancaService.getAll();
    }

    @POST
    @Transactional
    public CobrancaParcelada create(String json){
        CobrancaParceladaDTO cobrancaParceladaDTO = gson.fromJson(json, CobrancaParceladaDTO.class);
        return cobrancaService.create(cobrancaParceladaDTO);
    }
}
