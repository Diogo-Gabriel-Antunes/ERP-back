package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Produto;
import org.acme.services.ProdutoService;
import org.acme.services.EstoqueService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/produto")
@ApplicationScoped
public class ProductController {

    @Inject
    ProdutoService produtoService;
    @Inject
    EstoqueService estoqueService;

    private Gson gson = new GsonUtil().parser;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Produto> listAll() {
         return produtoService.listAll();
    }
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Produto listOne(@PathParam("id") String id) {
        return produtoService.getOneProduct(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(String json) {
        return estoqueService.create(json);
    }

    @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, String product) {
        return produtoService.updateProduct(id, product);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id")String id){
        produtoService.deleteProduct(id);
        return Response.ok("Produto deletado com sucesso").build();
    }
}
