package org.acme.controller;


import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.schema_4.enviNFe.TEnviNFe;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.Util.GsonUtil;
import org.acme.models.Compra;
import org.acme.models.DTO.CompraDTO;
import org.acme.services.CompraService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/compra")
@ApplicationScoped
public class CompraController {


    @Inject
    private CompraService compraService;
    @GET
    public Response findAll(){
        return Response.ok(Compra.findAll().list()).build();
    }
    @GET
    @Path("{uuid}")
    public Response findOne(@PathParam("uuid") String uuid){
        Compra compra = Compra.findById(uuid);
        return Response.ok(compra).build();
    }

    @POST
    @Transactional
    public Response create(String json){
        return compraService.create(json);
    }

    @PUT
    @Transactional
    @Path("{uuid}")
    public Response update(@PathParam("uuid")String uuid,String json){
        return compraService.update(uuid,json);
    }
    @DELETE
    @Transactional
    @Path("{uuid}")
    public Response delete(@PathParam("uuid")String uuid){
        return Response.ok(Compra.deleteById(uuid)).build();
    }

}
