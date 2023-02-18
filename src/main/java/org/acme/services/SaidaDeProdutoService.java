package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.ArrayUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.SaidaDeProdutoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class SaidaDeProdutoService extends Service {

    @Transactional
    public Response create(String json) {

        try{
            SaidaDeProdutoDTO saidaDeProdutoDTO = gson.fromJson(json, SaidaDeProdutoDTO.class);
            validaSaidaDeProduto(saidaDeProdutoDTO);
            SaidaDeProduto saidaDeProduto = new SaidaDeProduto();
            parseDTOtoModel(saidaDeProduto,saidaDeProdutoDTO);
            em.persist(saidaDeProduto);
            Estoque estoque = estoqueService.findByProduct(saidaDeProduto.getProduto());
            ValidacaoException validacaoException = estoque.validaQuantidade(saidaDeProduto);
            Estoque estoqueMerged = em.merge(estoque);
            Long quantidade = estoqueMerged.getQuantidade();
            estoque.setQuantidade(quantidade - saidaDeProduto.getQuantidade());
            em.persist(estoqueMerged);
            em.flush();
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
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto.getFuncionario(),saidaDeProdutoDTO.getFuncionario());
        Category category = em.merge(saidaDeProduto.getProduto().getCategoria());
        Produto produto = em.merge(saidaDeProduto.getProduto());
        Funcionario funcionario = em.merge(saidaDeProduto.getFuncionario());
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto,saidaDeProdutoDTO);
        saidaDeProduto.setProduto(produto);
        saidaDeProduto.getProduto().setCategoria(category);
        saidaDeProduto.setFuncionario(funcionario);
    }
    @Transactional
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

        if(saidaDeProdutoDTO.getFuncionario() == null){
            validacao.add("Retirante deve ser informado");
        }
        if(dataUtil.validaLocalDateTime(saidaDeProdutoDTO.getDataDaSaida())){
            validacao.add("Digite uma data valida");
        }
        produtoService.validaProduto(saidaDeProdutoDTO.getProduto(),validacao);

        validacao.lancaErro();

    }
}
