package org.acme.repository;

import org.acme.Util.FieldUtil;
import org.acme.models.Atividade;
import org.acme.models.DTO.AtividadeDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AtividadeRepository {
    @Inject
    EntityManager em;
    private FieldUtil fieldUtil = new FieldUtil();

    public void create(Atividade atividade, AtividadeDTO atividadeDTO){
        Atividade.getEntityManager().merge(atividade);
        fieldUtil.updateFieldsDtoToModel(atividade,atividadeDTO);
        Atividade.persist(atividade);
    }

}
