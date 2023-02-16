package org.acme.services;


import org.acme.Util.DateUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Cliente;
import org.acme.models.DTO.PedidoDTO;
import org.acme.models.Estoque;
import org.acme.models.Pedido;
import org.acme.models.StatusRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class PedidoService extends Service {



    public List<Pedido> findAll() {
        return em.createQuery("SELECT r FROM Pedido r", Pedido.class)
                .getResultList();
    }

    public Pedido findOne(String uuid) {
        return em.createQuery("SELECT r FROM Pedido r WHERE uuid = :uuid", Pedido.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public Response create(String json) {
        try{
            PedidoDTO pedidoDTO = gson.fromJson(json, PedidoDTO.class);

            Pedido pedido = new Pedido();
            AtomicReference<Double> valor = new AtomicReference<>((double) 0);

            fieldUtil.updateFieldsDtoToModel(pedido, pedidoDTO);

            pedido.setDataCriacao(LocalDateTime.now());
            pedido.setFinishDate(LocalDate.now());
            StatusRequest status = new StatusRequest();
            status.setUuid("1");
            pedido.setStatus(status);
            pedido.setNumberRequest(String.valueOf((long)(Math.random() * 1000000)));


            Pedido pedidoDoBanco = em.merge(pedido);
            pedidoDTO.getItens().forEach(item->{
                em.merge(item);
                valor.updateAndGet(v -> (double) (v + item.getProduto().getPrecoUnitario() * item.getQuantidade()));
            });
            em.merge(pedidoDoBanco);
            pedidoDoBanco.setValue(valor.get());

            em.persist(pedidoDoBanco);
            Cliente cliente = new Cliente();
            fieldUtil.updateFieldsDtoToModel(cliente,pedidoDTO.getCliente());
            Cliente clienteBD = Cliente.getEntityManager().merge(cliente);
            pedidoDoBanco.setCliente(clienteBD);
            em.persist(clienteBD);
            em.flush();
            return ResponseBuilder.responseOk(pedidoDoBanco);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public Response update(String uuid, String json) {
      try {
          PedidoDTO pedidoDTO = gson.fromJson(json, PedidoDTO.class);
          Pedido pedido = findOne(uuid);
          Cliente clienteBD = em.merge(pedido.getCliente());
          fieldUtil.updateFieldsDtoToModel(pedido, pedidoDTO);
          pedidoDTO.getItens().forEach(item->{
              item.getPedido().add(pedido);
              em.merge(item);
          });
          pedido.setFinishDate(LocalDate.now());
          pedido.setCliente(clienteBD);
          em.persist(pedido.getCliente());
          em.persist(pedido);

          em.flush();
          return ResponseBuilder.responseOk(pedido);
      }catch (ValidacaoException e){
          return ResponseBuilder.returnResponse(e);
      }catch (Throwable t){
          t.printStackTrace();
          return ResponseBuilder.returnResponse();
      }
    }

    public List<Pedido> findMonth() {
        LocalDate hoje = LocalDate.now();
        try{
            LocalDate umMesAtras = null;
            if(new DateUtil().validaData(hoje)){
                umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth()-1);
                return em.createQuery("SELECT r FROM Pedido r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status", Pedido.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }else{
                umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth());
                return em.createQuery("SELECT r FROM Pedido r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status", Pedido.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }
        }catch (DateTimeException e){
            LocalDate umMesAtras = null;
            if(new DateUtil().validaData(hoje)){
                umMesAtras = LocalDate.of(hoje.getYear()-1,12,hoje.getDayOfMonth()-1);
                return em.createQuery("SELECT r FROM Pedido r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status", Pedido.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }else{
                umMesAtras = LocalDate.of(hoje.getYear(),12,hoje.getDayOfMonth());
                return em.createQuery("SELECT r FROM Pedido r WHERE r.finishDate <= :hoje AND r.finishDate >= :umMesAtras AND r.status = :status", Pedido.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .setParameter("status",StatusRequest.findById("5"))
                        .getResultList();
            }
        }


    }

    public Response updateFinish(String uuid) {
        try{
            Pedido pedido = findOne(uuid);
            pedido.setStatus(StatusRequest.findById("5"));
            pedido.setFinishDate(LocalDate.now());
            pedido.getItens().forEach(item ->{
                Estoque storageBD = estoqueService.findByProduct(item.getProduto());
                Estoque.persist(storageBD);
                storageBD.setQuantidade(storageBD.getQuantidade() - item.getQuantidade());
                Estoque.persist(storageBD);
            });
            em.persist(pedido);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
