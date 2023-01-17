package org.acme.services;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.DateUtil;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.DTO.RequestDTO;
import org.acme.models.Request;
import org.acme.models.StatusRequest;
import org.acme.models.Storage;
import org.bouncycastle.ocsp.Req;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
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
    @Inject
    StorageService storageService;
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
        status.setStatus("Inicializado");
        StatusRequest statusRequest = em.merge(status);
        request.setStatus(statusRequest);
        request.setNumberRequest(String.valueOf((long)(Math.random() * 1000000)));


        Request pedidoDoBanco = em.merge(request);
        requestDTO.getItens().forEach(item->{
            item.getPedido().add(pedidoDoBanco);
            em.merge(item);
            valor.updateAndGet(v -> (double) (v + item.getProduto().getPrecoUnitario() * item.getQuantidade()));
        });
        em.merge(pedidoDoBanco);
        pedidoDoBanco.setValue(valor.get());

        em.persist(pedidoDoBanco);
        Cliente clienteBD = Cliente.getEntityManager().merge(requestDTO.getCliente());
        pedidoDoBanco.setCliente(clienteBD);
        em.persist(clienteBD);
        em.flush();
        return pedidoDoBanco;
    }

    public void update(String uuid, RequestDTO requestDTO) {
        Request request = findOne(uuid);
        Cliente clienteBD = em.merge(request.getCliente());
        fieldUtil.updateFieldsDtoToModel(request,requestDTO);
        requestDTO.getItens().forEach(item->{
            item.getPedido().add(request);
            em.merge(item);
        });
        request.setFinishDate(LocalDate.now());
        request.setCliente(clienteBD);
        em.persist(request.getCliente());
        em.persist(request);

        em.flush();
    }

    public List<Request> findMonth() {
        LocalDate hoje = LocalDate.now();
        try{
            LocalDate umMesAtras = null;
            if(new DateUtil().validaData(hoje)){
                umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth()-1);
                return em.createQuery("SELECT r FROM Request r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status",Request.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }else{
                umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth());
                return em.createQuery("SELECT r FROM Request r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status",Request.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }
        }catch (DateTimeException e){
            LocalDate umMesAtras = null;
            if(new DateUtil().validaData(hoje)){
                umMesAtras = LocalDate.of(hoje.getYear()-1,12,hoje.getDayOfMonth()-1);
                return em.createQuery("SELECT r FROM Request r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status",Request.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }else{
                umMesAtras = LocalDate.of(hoje.getYear(),12,hoje.getDayOfMonth());
                return em.createQuery("SELECT r FROM Request r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status",Request.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }
        }


    }

    public Response updateFinish(String uuid) {
        try{
            Request request = findOne(uuid);
            request.setStatus(StatusRequest.findById("5"));
            request.setFinishDate(LocalDate.now());
            request.getItens().forEach(item ->{
                Storage storageBD = storageService.findByProduct(item.getProduto());
                Storage.persist(storageBD);
                storageBD.setQuantidade(storageBD.getQuantidade() - item.getQuantidade());
                Storage.persist(storageBD);
            });
            em.persist(request);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
