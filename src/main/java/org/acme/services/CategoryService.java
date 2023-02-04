package org.acme.services;

import org.acme.models.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CategoryService extends Service {


    public List<Category> listAll(){
        return em.createQuery("SELECT c FROM Category c",Category.class).getResultList();
    }
    public Category getOne(String id){
        return em.createQuery("SELECT c FROM Category c WHERE id = :id",Category.class).setParameter("id",id).getSingleResult();
    }
}
