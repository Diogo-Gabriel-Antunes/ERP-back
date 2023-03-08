package org.acme.services;

import org.acme.models.Categoria;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CategoryService extends Service {


    public List<Categoria> listAll(){
        return em.createQuery("SELECT c FROM Category c", Categoria.class).getResultList();
    }
    public Categoria getOne(String id){
        return em.createQuery("SELECT c FROM Category c WHERE id = :id", Categoria.class).setParameter("id",id).getSingleResult();
    }
}
