package org.acme.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acme.Util.GsonUtil;
import org.acme.Util.LocalDateAdapter;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.DTO.VendasDTO;
import org.acme.models.Product;
import org.acme.models.Venda;
import org.acme.services.ProductServices;
import org.acme.services.StorageService;
import org.acme.services.VendasService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/vendas")
@ApplicationScoped
public class VendasController {

    @Inject
    VendasService vendasService;


    private Gson gson = new GsonUtil().parser;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Venda> listAll() {
        return Venda.listAll();
    }
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Venda listOne(@PathParam("id") String id) {
        return Venda.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(String venda) {
        Venda vendas = gson.fromJson(venda,Venda.class);
        Venda.persist(vendas);
        return Response.ok(vendas).build();
    }

    @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, String json) {
        VendasDTO vendasDTO = gson.fromJson(json, VendasDTO.class);

        return vendasService.update(id,vendasDTO);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id")String id){
        Venda.deleteById(id);
        return Response.ok("Venda deletada com sucesso").build();
    }
}
