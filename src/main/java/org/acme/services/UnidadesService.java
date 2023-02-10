package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.DTO.UnidadeDTO;
import org.acme.models.Nota_fiscal_eletronica.Pessoa;
import org.acme.models.Unidade;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class UnidadesService extends Service{


    public List<Unidade> findAll(){
        return em.createQuery("SELECT u FROM Unidade u",Unidade.class).getResultList();
    }

    public void create(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        fieldUtil.updateFieldsDtoToModel(unidade,unidadeDTO);
        Pessoa pessoaMerged = em.merge(unidade.getPessoa());
        unidade.setPessoa(pessoaMerged);
        em.merge(unidade);
    }

    public Unidade findOne(String uuid) {
        return em.createQuery("SELECT u FROM Unidade u WHERE uuid = :uuid", Unidade.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void update(String uuid, UnidadeDTO unidadeDTO) {
        Unidade unidade= findOne(uuid);
        em.merge(unidade.getPessoa());
        em.merge(unidade);
        fieldUtil.updateFieldsDtoToModel(unidade,unidadeDTO);

        unidadeDTO.setAtualizadoEm(LocalDate.now());
        em.persist(unidade.getPessoa());
        em.persist(unidade);
        em.flush();
    }

    public Response delete(String uuid) {
        Unidade unidade = findOne(uuid);
        em.remove(unidade);
        return Response.ok().build();
    }
}
