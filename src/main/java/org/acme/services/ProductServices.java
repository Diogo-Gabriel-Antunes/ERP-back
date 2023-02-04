package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.FieldUtil;
import org.acme.models.Category;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.Imposto;
import org.acme.models.Product;
import org.acme.models.enums.Status;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductServices extends Service{


    public List<Product> listAll() {
        return em.createQuery("SELECT p from Product p", Product.class).getResultList();
    }

    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        productDTO.setDataCriacao(LocalDate.now());
        productDTO.setDataAlteracao(LocalDate.now());
        productDTO.setStatus(true);
        Category category = Category.findById(productDTO.getCategoria().getUuid());
        productDTO.setCategoria(category);
        fieldUtil.updateFieldsDtoToModel(product,productDTO);
        Product productMerge = em.merge(product);
        product.getImposto().forEach(imposto -> {
        });
        return productMerge;
    }

    public Product getOneProduct(String id) {
        return em.createQuery("SELECT p from Product p WHERE uuid = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Product updateProduct(String id, ProductDTO newProduct) {
        Product product = getOneProduct(id);

        em.merge(product);

        fieldUtil.updateFieldsDtoToModel(product,newProduct);

        em.persist(product);
        return product;
    }


    public void deleteProduct(String id) {
        em.createQuery("delete from Product WHERE uuid = :id")
                .setParameter("id",id).executeUpdate();

    }
}
