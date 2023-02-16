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

    public Atividade create(Atividade newAtividade, AtividadeDTO atividadeDTO){
        Atividade atividade = Atividade.getEntityManager().merge(newAtividade);
        fieldUtil.updateFieldsDtoToModel(atividade,atividadeDTO);
        return atividade;
    }

}
