package org.acme.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.acme.Util.GsonUtil;
import org.acme.Util.LocalDateAdapter;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.Product;
import org.acme.services.ProductServices;
import org.acme.services.StorageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/product")
@ApplicationScoped
public class ProductController {

    @Inject
    private ProductServices productServices;
    @Inject
    private StorageService storageService;

    private Gson gson = new GsonUtil().parser;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listAll() {
        return productServices.listAll();
    }
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Product listOne(@PathParam("id") String id) {
        return productServices.getOneProduct(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(String product) {
        Product productCreated = gson.fromJson(product, Product.class);

        Product productInBase = productServices.createProduct(productCreated);
        storageService.create(productInBase);
        return Response.ok(productInBase).build();
    }

    @Path("{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") String id, String product) {
        ProductDTO productDTO = gson.fromJson(product, ProductDTO.class);
        Product newProduct = productServices.updateProduct(id, productDTO);
        return Response.ok(newProduct).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id")String id){
        productServices.deleteProduct(id);
        return Response.ok("Produto deletado com sucesso").build();
    }
}
