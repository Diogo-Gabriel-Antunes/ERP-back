package org.acme.services;

import org.acme.Util.JsonUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.CompraDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class CompraService extends Service {

    public Response create(String json) {
        try {
            json = JsonUtil.preValidate(json, CompraDTO.class);
            if(json.contains("erro")){
                return ResponseBuilder.returnResponseErro(json);
            }
            CompraDTO compraDTO = gson.fromJson(json, CompraDTO.class);
            validaCompra(compraDTO);
            Compra compra = new Compra();
            Compra.getEntityManager().merge(compra);
            fieldUtil.updateFieldsDtoToModel(compra,compraDTO);
            Compra.getEntityManager().persist(compra);
            Compra.flush();
            return ResponseBuilder.responseOk(compra);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    private void validaCompra(CompraDTO compraDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(compraDTO.getItens().isEmpty()){
            validacao.add("Voce precisa adicionar itens");
        }
        if(compraDTO.getResponsavelPelaVenda() == null){
            validacao.add("É necessario adicionar o responsavel pela venda");
        }
        validacao.lancaErro();
    }

    public Response update(String uuid, String json) {
        CompraDTO compraDTO = gson.fromJson(json, CompraDTO.class);
        validaCompra(compraDTO);
        Compra compra = Compra.findById(uuid);
        fieldUtil.updateFieldsDtoToModel(compra,compraDTO);
        Compra.persist(compra);
        return Response.ok(compra).build();
    }
}
