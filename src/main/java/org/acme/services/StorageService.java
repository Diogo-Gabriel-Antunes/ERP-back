package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.DTO.StorageDTO;
import org.acme.models.Product;
import org.acme.models.Storage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class StorageService {

    @Inject
    EntityManager em;
    @Inject
    FieldUtil fieldUtil;
    public Storage create(Product product){
        Storage storage = new Storage();
        storage.setProduct(product);
        storage.setLastUpdate(LocalDate.now());
        storage.setAmount(0L);
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
        fieldUtil.updateFields(newStorage,storageDTO);
        newStorage.setLastUpdate(LocalDate.now());
        em.persist(newStorage);
        return newStorage;
    }
}
