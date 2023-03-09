package org.acme.services;


import org.acme.Util.JsonUtil;
import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.Util.DateUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.PedidoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

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
            json = JsonUtil.preValidate(json,PedidoDTO.class);
            if(json.contains("erro")){
                return ResponseBuilder.returnResponseErro(json);
            }
            PedidoDTO pedidoDTO = gson.fromJson(json, PedidoDTO.class);
            validaPedido(pedidoDTO);

            Pedido pedido = new Pedido();
            convertDTO(pedido,pedidoDTO);
            em.persist(pedido);
            return ResponseBuilder.responseOk(pedido);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private void convertDTO(Pedido pedido, PedidoDTO pedidoDTO) {
        fieldUtil.updateFieldsDtoToModel(pedido,pedidoDTO);
        pedidoDTO.getItens().forEach(itemDTO->{
            Itens item = Itens.findById(itemDTO.getUuid());
            pedido.getItens().add(item);
        });
        Cliente cliente = Cliente.findById(pedidoDTO.getUuid());
        pedido.setCliente(cliente);
    }


    public Response update(String uuid, String json) {
      try {

          PedidoDTO pedidoDTO = gson.fromJson(json, PedidoDTO.class);
          validaPedido(pedidoDTO);

          Pedido pedido = new Pedido();
          convertDTO(pedido,pedidoDTO);
          em.persist(pedido);
          return ResponseBuilder.responseOk(pedido);
      }catch (ValidacaoException e){
          return ResponseBuilder.returnResponse(e);
      }catch (Throwable t){
          t.printStackTrace();
          return ResponseBuilder.returnResponse();
      }
    }
    public void validaPedido(PedidoDTO pedidoDTO) {
        ValidacaoException validacaoException = new ValidacaoException();

        if(pedidoDTO.getCliente() == null){
            validacaoException.add("O Cliente deve ser informado");
        }
        if(!StringUtil.stringValida(pedidoDTO.getNumberRequest())){
            validacaoException.add("Campo numero do pedido esta invalido");
        }
        if(!ArrayUtil.validaArray(pedidoDTO.getItens())){
            validacaoException.add("O campo itens esta invalido");
        }
        if(pedidoDTO.getStatus() == null){
            validacaoException.add("O campo status esta invalido");
        }
        pedidoDTO.getItens().forEach(item->{
            estoqueService.validaQuantidadeMinima(item);
        });
        validacaoException.lancaErro();
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
