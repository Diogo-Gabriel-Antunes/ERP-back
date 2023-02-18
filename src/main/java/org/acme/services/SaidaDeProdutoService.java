package org.acme.services;

import com.google.gson.JsonSyntaxException;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.ArrayUtil;
import org.acme.Util.DataUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.SaidaDeProdutoDTO;
import org.acme.models.Estoque;
import org.acme.models.SaidaDeProduto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class SaidaDeProdutoService extends Service {

    public Response create(String json) {
        SaidaDeProduto saidaDeProduto = null;

        try{
            SaidaDeProdutoDTO saidaDeProdutoDTO = gson.fromJson(json, SaidaDeProdutoDTO.class);
            validaSaidaDeProduto(saidaDeProdutoDTO);
            saidaDeProduto = new SaidaDeProduto();
            parseDTOtoModel(saidaDeProduto,saidaDeProdutoDTO);
            em.persist(saidaDeProduto);
            Estoque estoque = estoqueService.findByProduct(saidaDeProduto.getProduto());
            ValidacaoException validacaoException = estoque.validaQuantidade(saidaDeProduto);
            Estoque estoqueMerged = em.merge(estoque);
            Long quantidade = estoqueMerged.getQuantidade();
            estoque.setQuantidade(quantidade - saidaDeProduto.getQuantidade());
            em.persist(estoqueMerged);
            if(ArrayUtil.validaArray(validacaoException.getValidacoes())){
                return ResponseBuilder.respondeOkWithAlert(saidaDeProduto,validacaoException);
            }else{
                return ResponseBuilder.responseOk(saidaDeProduto);
            }
        }catch (JsonSyntaxException j){
            j.printStackTrace();
            return ResponseBuilder.returnNumberFormat();
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public void parseDTOtoModel(SaidaDeProduto saidaDeProduto, SaidaDeProdutoDTO saidaDeProdutoDTO) {
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto.getProduto(),saidaDeProdutoDTO.getProduto());
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto.getRetirante(),saidaDeProdutoDTO.getRetirante());
        em.persist(saidaDeProduto.getProduto());
        em.persist(saidaDeProduto.getRetirante());
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto,saidaDeProdutoDTO);
    }

    public Response update(String uuid,String json) {
        try{
            SaidaDeProdutoDTO saidaDeProdutoDTO = gson.fromJson(json, SaidaDeProdutoDTO.class);
            validaSaidaDeProduto(saidaDeProdutoDTO);
            Optional<SaidaDeProduto> saidaDeProdutoOp = SaidaDeProduto.findByIdOptional(uuid);
            if(saidaDeProdutoOp.isPresent()){
                Estoque estoque = estoqueService.findByProduct(saidaDeProdutoOp.get().getProduto());
                ValidacaoException validacaoException = estoque.validaQuantidade(saidaDeProdutoOp.get());
                parseDTOtoModel(saidaDeProdutoOp.get(),saidaDeProdutoDTO);
                em.persist(saidaDeProdutoOp.get());
                if(ArrayUtil.validaArray(validacaoException.getValidacoes())){
                    return ResponseBuilder.respondeOkWithAlert(saidaDeProdutoOp.get(),validacaoException);
                }else{
                    return ResponseBuilder.responseOk(saidaDeProdutoOp.get());
                }
            }else{
                return ResponseBuilder.responseEntityNotFound();
            }
        }catch (JsonSyntaxException j){
            j.printStackTrace();
            return ResponseBuilder.returnNumberFormat();
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }
    private void validaSaidaDeProduto(SaidaDeProdutoDTO saidaDeProdutoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(saidaDeProdutoDTO.getRetirante() == null){
            validacao.add("Retirante deve ser informado");
        }
        if(dataUtil.validaLocalDateTime(saidaDeProdutoDTO.getDataDaSaida())){
            validacao.add("Digite uma data valida");
        }
        produtoService.validaProduto(saidaDeProdutoDTO.getProduto(),validacao);

        validacao.lancaErro();

    }
}
