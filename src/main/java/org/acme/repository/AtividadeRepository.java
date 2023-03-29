package org.acme.repository;

import org.acme.models.Atividade;
import org.acme.models.DTO.AtividadeDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class AtividadeRepository {

    public Atividade create(Atividade newAtividade, AtividadeDTO atividadeDTO){
        Atividade atividade = Atividade.getEntityManager().merge(newAtividade);
        return atividade;
    }

}
