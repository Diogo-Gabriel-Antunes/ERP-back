package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.JsonUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.TipoDeLoteDTO;
import org.acme.models.TipoDeLote;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class TipoLoteService extends Service {

    @Transactional
    public Response create(String json) {
        try {

            TipoDeLoteDTO tipoDeLoteDTO = gson.fromJson(json, TipoDeLoteDTO.class);
            validaTipoDeLote(tipoDeLoteDTO);
            TipoDeLote tipoDeLote = new TipoDeLote();
            fieldUtil.updateFieldsDtoToModel(tipoDeLote, tipoDeLoteDTO);
            em.persist(tipoDeLote);
            em.flush();
            return ResponseBuilder.responseOk(tipoDeLote);
        } catch (JsonSyntaxException j) {
            j.printStackTrace();
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }


    @Transactional
    public Response update(String uuid, String json) {
        try {
            TipoDeLoteDTO tipoDeLoteDTO = gson.fromJson(json, TipoDeLoteDTO.class);
            validaTipoDeLote(tipoDeLoteDTO);
            TipoDeLote tipoDeLote = new TipoDeLote();
            fieldUtil.updateFieldsDtoToModel(tipoDeLote, tipoDeLoteDTO);
            em.persist(tipoDeLote);
            em.flush();
            return ResponseBuilder.responseOk(tipoDeLote);
        } catch (JsonSyntaxException j) {
            j.printStackTrace();
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private void validaTipoDeLote(TipoDeLoteDTO tipoDeLoteDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if (!StringUtil.stringValida(tipoDeLoteDTO.getTipoLote())) {
            validacao.add("Campo tipo de lote invalido");
        }
        if (tipoDeLoteDTO.getQuantidadeMaxEmLote() <= 0) {
            validacao.add("Campo quantidade maxima em lote deve ser maior que 0");
        }

        validacao.lancaErro();
    }
}
