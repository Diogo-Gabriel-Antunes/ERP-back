package org.acme.repository;

import org.acme.models.DTO.ItensDTO;
import org.acme.models.consulta.ProdutoEstoque;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class ProdutoRepository {
    @Inject
    EntityManager em;
    public ProdutoEstoque findProdutoEstoque(ItensDTO item){
        return em.createQuery("SELECT p,e FROM Produto p" +
                " JOIN Estoque e WHERE p.uuid = :uuid ",ProdutoEstoque.class)
                .setParameter("uuid",item.getProduto().getUuid())
                .getSingleResult();
    }
}
