package org.acme.controller;

import org.acme.services.FluxoDeCaixaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/relatorio")
public class RelatorioFinanceiroController {

    @Inject
    FluxoDeCaixaService fluxoDeCaixaService;
    @GET
    @Path("fluxodecaixa")
    public Response fluxoDeCaixa(){
        try{
            Double fluxoDeCaixa = fluxoDeCaixaService.getFluxoDeCaixa();
            return Response.ok(fluxoDeCaixa).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
