package org.acme.controller;

import com.google.gson.Gson;
import org.acme.models.Categoria;
import org.acme.services.CategoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@ApplicationScoped
public class CategoryController {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Categoria> listAll(){
        return categoryService.listAll();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("{uuid}")
    public Categoria listOne(@PathParam("uuid")String uuid){
        return Categoria.findById(uuid);
    }

}
