package org.acme.services;

import org.acme.Util.DateUtil;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.StorageDTO;
import org.acme.models.Product;
import org.acme.models.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class StorageService extends Service{


    public Storage create(Product product){
        Storage storage = new Storage();
        storage.setProduto(product);
        storage.setUltimaAtualizacao(LocalDate.now());
        storage.setQuantidade(0L);
        return em.merge(storage);
    }

    public List<Storage> findAll() {
        return em.createQuery("SELECT s FROM Storage s",Storage.class)
                .getResultList();
    }

    public Storage findOne(String uuid) {
        return em.createQuery("SELECT s FROM Storage s WHERE uuid = :id",Storage.class)
                .setParameter("id",uuid)
                .getSingleResult();
    }

    public Storage update(String uuid,StorageDTO storageDTO) {
        Storage storage = findOne(uuid);
        Storage newStorage = em.merge(storage);
        fieldUtil.updateFieldsDtoToModel(newStorage,storageDTO);
        newStorage.setUltimaAtualizacao(LocalDate.now());
        em.persist(newStorage);
        return newStorage;
    }
    public Storage findByProduct(Product product){
        return em.createQuery("SELECT e FROM Storage e WHERE e.produto.uuid = :uuid", Storage.class)
                .setParameter("uuid", product.getUuid()).getSingleResult();
    }

    public List<Storage> findMonth() {
        LocalDate hoje = LocalDate.now();
        try{
            LocalDate umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue() -1 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM Storage s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Storage.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM Storage s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Storage.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }catch (DateTimeException e){
            LocalDate umMesAtras = LocalDate.of(hoje.getYear()-1,12 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM Storage s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Storage.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM Storage s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",Storage.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }


    }


}