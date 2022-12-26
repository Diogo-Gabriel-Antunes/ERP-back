package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.DTO.OrdemDeProducaoDTO;
import org.acme.models.Endereco;
import org.acme.models.OrdemDeProducao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class OrdemDeProducaoService {
    @Inject
    EntityManager em;
    private FieldUtil fieldUtil = new FieldUtil();

    public List<OrdemDeProducao> findAll(){
        return em.createQuery("SELECT o FROM OrdemDeProducao o", OrdemDeProducao.class).getResultList();
    }

    public void create(OrdemDeProducaoDTO ordemDeProducaoDTO) {
        OrdemDeProducao ordemDeProducao = new OrdemDeProducao();

        fieldUtil.updateFieldsDtoToModel(ordemDeProducao,ordemDeProducaoDTO);
        ordemDeProducao.setInicioDaProducao(LocalDate.now());
        em.merge(ordemDeProducao);
    }

    public OrdemDeProducao findOne(String uuid) {
        return em.createQuery("SELECT o FROM OrdemDeProducao o WHERE uuid = :uuid",OrdemDeProducao.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void update(String uuid, OrdemDeProducaoDTO ordemDeProducaoDTO) {
        OrdemDeProducao ordemDeProducao= findOne(uuid);

        em.merge(ordemDeProducao);
        fieldUtil.updateFieldsDtoToModel(ordemDeProducao,ordemDeProducaoDTO);
        ordemDeProducao.setInicioDaProducao(LocalDate.now());
        em.persist(ordemDeProducao);
        em.flush();
    }

    public void delete(String uuid) {
        OrdemDeProducao ordemDeProducao = findOne(uuid);
        em.remove(ordemDeProducao);

    }
}
