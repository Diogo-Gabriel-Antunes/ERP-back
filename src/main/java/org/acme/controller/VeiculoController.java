package org.acme.controller;

import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.services.VeiculoService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("veiculo")
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;
    @GET
    public Response listAll(){
        return veiculoService.listAll();
    }
}
