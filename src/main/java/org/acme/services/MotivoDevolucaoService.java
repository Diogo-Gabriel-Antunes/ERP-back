package org.acme.services;

import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.MotivoDaDevolucaoDTO;
import org.acme.models.MotivoDaDevolucao;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Optional;

@ApplicationScoped
public class MotivoDevolucaoService extends Service {

    @Transactional
    public Response create(String json) {

            MotivoDaDevolucaoDTO motivoDaDevolucaoDTO = gson.fromJson(json, MotivoDaDevolucaoDTO.class);
            validaMotivoDevolucao(motivoDaDevolucaoDTO);
            MotivoDaDevolucao motivo = new MotivoDaDevolucao();
            motivo.setDataCriacao(LocalDate.now());
            convertDTO(motivo,motivoDaDevolucaoDTO);
            motivo.persistAndFlush();
            return ResponseBuilder.responseOk(motivo);

    }
    @Transactional
    public Response update(String uuid, String json) {
            Optional<MotivoDaDevolucao> motivo = MotivoDaDevolucao.findByIdOptional(uuid);
            if(motivo.isPresent()){
                MotivoDaDevolucaoDTO motivoDaDevolucaoDTO = gson.fromJson(json, MotivoDaDevolucaoDTO.class);
                validaMotivoDevolucao(motivoDaDevolucaoDTO);
                em.persist(motivo.get());
                fieldUtil.updateFieldsDtoToModel(motivo.get(),motivoDaDevolucaoDTO);
                em.flush();
                return ResponseBuilder.responseOk(motivo.get());
            }else{
                return ResponseBuilder.responseEntityNotFound();
            }
    }
    private void convertDTO(MotivoDaDevolucao motivoDaDevolucao,MotivoDaDevolucaoDTO motivoDaDevolucaoDTO) {
        fieldUtil.updateFieldsDtoToModel(motivoDaDevolucao,motivoDaDevolucaoDTO);
    }

    private void validaMotivoDevolucao(MotivoDaDevolucaoDTO motivoDaDevolucaoDTO) {
        ValidacaoException validacao = new ValidacaoException();
        if(motivoDaDevolucaoDTO.getMotivo() == null){
            validacao.add("É Necessario adicionar um motivo");
        }

        validacao.lancaErro();
    }


}
