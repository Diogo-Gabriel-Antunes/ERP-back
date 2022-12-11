package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Category;
import org.acme.services.CategoryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
@ApplicationScoped
public class CategoryController {

    @Inject
    private CategoryService categoryService;
    private Gson gson = new GsonUtil().parser;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Category> listAll(){
        return categoryService.listAll();
    }

}
