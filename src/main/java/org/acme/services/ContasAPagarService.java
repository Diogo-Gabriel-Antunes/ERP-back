package org.acme.services;

import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.DTO.Financas.ContasAPagarDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ContasAPagarService extends Service {

    public List<ContasAPagar> listAll() {
        return em.createQuery("SELECT c from ContasAPagar c", ContasAPagar.class).getResultList();
    }

    public ContasAPagar create(ContasAPagarDTO contasAPagarDTO) {
        ContasAPagar contasAPagar = new ContasAPagar();
        fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);
        em.merge(contasAPagar);
        return contasAPagar;
    }

    public ContasAPagar getOne(String uuid) {
        return em.createQuery("SELECT c from ContasAPagar c WHERE uuid = :uuid", ContasAPagar.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    public ContasAPagar update(String id, ContasAPagarDTO contasAPagarDTO) {
        ContasAPagar contasAPagar = getOne(id);

        em.merge(contasAPagar);

        fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);

        em.persist(contasAPagar);
        return contasAPagar;
    }


    public void delete(String uuid) {
        em.createQuery("delete from ContasAPagar WHERE uuid = :uuid")
                .setParameter("uuid",uuid).executeUpdate();

    }
}
