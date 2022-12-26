package org.acme.controller;

import org.acme.models.cobranca.CobrancaParceladaRetorno;
import org.acme.services.CobrancaRetornoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/cobrancaretorno")
public class CobrancaRetornoController {

    @Inject
    CobrancaRetornoService cobrancaRetornoService;
    @GET
    public List<CobrancaParceladaRetorno> listAll(){
        return cobrancaRetornoService.getAll();
    }
}
