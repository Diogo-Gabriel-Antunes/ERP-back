package org.acme.services;


import org.acme.Util.FieldUtil;
import org.acme.models.DTO.RequestDTO;
import org.acme.models.Request;
import org.acme.models.StatusRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class RequestService {

    @Inject
    EntityManager em;
    @Inject
    FieldUtil fieldUtil;
    @Inject
    ClienteService clienteService;
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
        AtomicReference<Double> valor = new AtomicReference<>((double) 0);

        fieldUtil.updateFieldsDtoToModel(request,requestDTO);

        request.setCreateDate(LocalDate.now());
        request.setFinishDate(LocalDate.now());

        StatusRequest status = new StatusRequest();
        status.setUuid("1");
        request.setStatus(status);
        request.setNumberRequest(String.valueOf((long)(Math.random() * 1000000)));


        Request pedidoDoBanco = em.merge(request);
        requestDTO.getItens().forEach(item->{
            item.setPedido(pedidoDoBanco);
            em.merge(item);
            valor.updateAndGet(v -> (double) (v + item.getProduct().getValue() * item.getQuantidade()));
        });
        em.merge(pedidoDoBanco);
        pedidoDoBanco.setValue(valor.get());
        em.persist(pedidoDoBanco);
        em.flush();
        return pedidoDoBanco;
    }

    public void update(String uuid, RequestDTO requestDTO) {
        Request request = findOne(uuid);
//        Cliente cliente = clienteService.findOne(requestDTO.getCliente().getUuid());
        em.merge(request);
        em.merge(requestDTO.getCliente());
        requestDTO.getItens().forEach(item->{
            item.setPedido(request);
            em.merge(item);
        });
        fieldUtil.updateFieldsDtoToModel(request,requestDTO);
        request.setFinishDate(LocalDate.now());
        em.persist(request);
    }
}
