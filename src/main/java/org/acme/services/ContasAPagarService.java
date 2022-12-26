package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.ContasAPagar;
import org.acme.models.DTO.ContasAPagarDTO;
import org.acme.models.DTO.ProductDTO;
import org.acme.models.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ContasAPagarService {
    @Inject
    EntityManager em;

    @Inject
    FieldUtil fieldUtil;
    public List<ContasAPagar> listAll() {
        return em.createQuery("SELECT c from ContasAPagar c", ContasAPagar.class).getResultList();
    }

    public ContasAPagarDTO create(ContasAPagarDTO contasAPagarDTO) {
        ContasAPagar contasAPagar = new ContasAPagar();
        fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);
        em.merge(contasAPagar);
        return contasAPagarDTO;
    }

    public ContasAPagar getOne(String uuid) {
        return em.createQuery("SELECT c from ContasAPagar c WHERE uuid = :uuid", ContasAPagar.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    public ContasAPagarDTO update(String id, ContasAPagarDTO contasAPagarDTO) {
        ContasAPagar contasAPagar = getOne(id);

        em.merge(contasAPagar);

        fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);

        em.persist(contasAPagar);
        return contasAPagarDTO;
    }


    public void delete(String uuid) {
        em.createQuery("delete from ContasAPagar WHERE uuid = :uuid")
                .setParameter("uuid",uuid).executeUpdate();

    }
}
