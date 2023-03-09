package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.JsonUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.DevolucaoDTO;
import org.acme.models.*;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class DevolucaoService extends Service {

    @Transactional
    public Response create(String json) {

            DevolucaoDTO devolucaoDTO = gson.fromJson(json, DevolucaoDTO.class);
            validaDevolucao(devolucaoDTO);
            Devolucao devolucao = new Devolucao();
            convertDTO(devolucao,devolucaoDTO);
            devolucao.persistAndFlush();
            return ResponseBuilder.responseOk(devolucao);

    }
    @Transactional
    public Response update(String uuid, String json) {
            Optional<Devolucao> devolucao = Devolucao.findByIdOptional(uuid);
            if(devolucao.isPresent()){
                DevolucaoDTO devolucaoDTO = gson.fromJson(json, DevolucaoDTO.class);
                validaDevolucao(devolucaoDTO);
                em.persist(devolucao.get());
                fieldUtil.updateFieldsDtoToModel(devolucao.get(),devolucaoDTO);
                em.flush();
                return ResponseBuilder.responseOk(devolucao.get());
            }else{
                return ResponseBuilder.responseEntityNotFound();
            }
    }
    private void convertDTO(Devolucao devolucao,DevolucaoDTO devolucaoDTO) {
        Pedido pedido = pedidoService.findOne(devolucaoDTO.getPedido().getUuid());
        fieldUtil.updateFieldsDtoToModel(devolucao,devolucaoDTO);
        devolucao.setPedido(pedido);
    }

    private void validaDevolucao(DevolucaoDTO devolucaoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(devolucaoDTO.getPedido() != null){
            if(devolucaoDTO.getPedido().getCliente() == null){
                validacao.add("Não foi informado cliente no pedido");
            }
            if(devolucaoDTO.getPedido().getNumberRequest() == null){
                validacao.add("É necessario informador o numero do pedido");
            }
        }else{
            validacao.add("É necessario informar o pedido");
        }
        if(devolucaoDTO.getDataDaDevolucao() == null){
            validacao.add("É necessario informar a data da devolução");
        }
        if(devolucaoDTO.getMotivoDevolucao() == null){
            validacao.add("É necessario informar o motivo da devolução");
        }

        validacao.lancaErro();
    }


}
