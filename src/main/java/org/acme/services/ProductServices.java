package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.Category;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.Product;
import org.acme.models.enums.Status;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ProductServices {
    @Inject
    EntityManager em;
    @Inject
    CategoryService categoryService;
    @Inject
    FieldUtil fieldUtil;
    public List<Product> listAll() {
        return em.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    public Product createProduct(Product Product) {
        Product.setDateCreate(LocalDate.now());
        Product.setDataAlteracao(LocalDate.now());
        Product.setStatus(true);
        return em.merge(Product);
    }

    public Product getOneProduct(String id) {
        return em.createQuery("SELECT p from Product p WHERE uuid = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Product updateProduct(String id, ProductDTO newProduct) {
        Product product = getOneProduct(id);

        em.merge(product);

        fieldUtil.updateFields(product,newProduct);

        em.persist(product);
        return product;
    }


    public void deleteProduct(String id) {
        em.createQuery("delete from Product WHERE uuid = :id")
                .setParameter("id",id).executeUpdate();

    }
}
