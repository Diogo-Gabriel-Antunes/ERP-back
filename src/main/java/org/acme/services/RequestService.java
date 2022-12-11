package org.acme.services;


import org.acme.Util.FieldUtil;
import org.acme.models.DTO.RequestDTO;
import org.acme.models.Request;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class RequestService {

    @Inject
    EntityManager em;
    @Inject
    FieldUtil fieldUtil;

    public List<Request> findAll() {
        return em.createQuery("SELECT r FROM Request r",Request.class)
                .getResultList();
    }

    public Request findOne(String uuid) {
        return em.createQuery("SELECT r FROM Request r WHERE uuid = :uuid",Request.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public Request create(RequestDTO requestDTO) {
        Request request = new Request();

        fieldUtil.updateFields(request,requestDTO);
        request.setCreateDate(LocalDate.now());
        request.setFinishDate(LocalDate.now());
        em.persist(request);
        return request;
    }

    public void update(String uuid, RequestDTO requestDTO) {
        Request request = findOne(uuid);
        em.merge(request);
        fieldUtil.updateFields(request,requestDTO);
        request.setFinishDate(LocalDate.now());
        em.persist(request);
    }
}
