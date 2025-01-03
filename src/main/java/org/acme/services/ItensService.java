package org.acme.services;

import org.acme.Util.DateUtil;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.Itens;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItensService extends Service {


    public Itens create(Produto produto){
        Itens itens = new Itens();
        itens.setProduto(produto);
        itens.setQuantidade(0L);
        return em.merge(itens);
    }

    public List<Itens> findAll() {
        return em.createQuery("SELECT s FROM Itens s",Itens.class)
                .getResultList();
    }

    public Itens findOne(String uuid) {
        return em.createQuery("SELECT s FROM Itens s WHERE uuid = :id",Itens.class)
                .setParameter("id",uuid)
                .getSingleResult();
    }

    public Itens update(String uuid, ItensDTO itensDTO) {
        Itens itens = findOne(uuid);
        Itens newItens = em.merge(itens);
        fieldUtil.updateFieldsDtoToModel(newItens,itensDTO);

        em.persist(newItens);
        return newItens;
    }
    public Itens findByProduct(String productUuid){
        return em.createQuery("SELECT e FROM Itens e WHERE e.produto.uuid = :uuid", Itens.class)
                .setParameter("uuid", productUuid).getSingleResult();
    }

    public List<Itens> findMonth() {
        LocalDate hoje = LocalDate.now();
        try{
            LocalDate umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue() -1 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM Itens s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Itens.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM Itens s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Itens.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }catch (DateTimeException e){
            LocalDate umMesAtras = LocalDate.of(hoje.getYear()-1,12 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM Itens s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Itens.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM Itens s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Itens.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }


    }

    public void convertDtoToModel(List<Itens> itens, ItensDTO itensDTO){
        if(itens == null){
            itens = new ArrayList<Itens>();
        }
        Itens itensModel = new Itens();

        fieldUtil.updateFieldsDtoToModel(itensModel,itensDTO);
        itens.add(itensModel);
        em.persist(itensModel);
    }

}
