package org.acme.controller;

import org.acme.models.enums.StatusDaProducao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/statusdaproducao")
public class StatusDaProducaoController {

    @GET
    public List<StatusDaProducao> listAll(){
        return StatusDaProducao.getAll();
    }
}
