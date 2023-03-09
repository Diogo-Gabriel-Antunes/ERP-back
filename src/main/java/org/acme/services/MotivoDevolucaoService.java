package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.JsonUtil;
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
        try {

            MotivoDaDevolucaoDTO motivoDaDevolucaoDTO = gson.fromJson(json, MotivoDaDevolucaoDTO.class);
            validaMotivoDevolucao(motivoDaDevolucaoDTO);
            MotivoDaDevolucao motivo = new MotivoDaDevolucao();
            motivo.setDataCriacao(LocalDate.now());
            convertDTO(motivo,motivoDaDevolucaoDTO);
            motivo.persistAndFlush();
            return ResponseBuilder.responseOk(motivo);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();

        }

    }
    @Transactional
    public Response update(String uuid, String json) {
        try {
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
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();

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
