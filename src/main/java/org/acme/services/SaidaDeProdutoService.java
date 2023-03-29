package org.acme.services;

import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.SaidaDeProdutoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class SaidaDeProdutoService extends Service {

    @Transactional
    public Response create(String json) {
        AtomicReference<ValidacaoException> validacaoException = new AtomicReference<>(new ValidacaoException());

            SaidaDeProdutoDTO saidaDeProdutoDTO = gson.fromJson(json, SaidaDeProdutoDTO.class);
            validaSaidaDeProduto(saidaDeProdutoDTO);
            SaidaDeProduto saidaDeProduto = new SaidaDeProduto();
            parseDTOtoModel(saidaDeProduto,saidaDeProdutoDTO);
            em.persist(saidaDeProduto);
            saidaDeProduto.getProduto().forEach(produto->{
                Estoque estoque = estoqueService.findByProduct(produto);
                validacaoException.set(estoque.validaQuantidade(saidaDeProduto));
                validacaoException.get().lancaErro();
                materiaPrimaService.validaQuantidadeDeMateriaPrima(produto,saidaDeProduto,validacaoException.get());
                validacaoException.get().lancaErro();
                Estoque estoqueMerged = em.merge(estoque);
                Long quantidade = estoqueMerged.getQuantidade();
                estoque.setQuantidade(quantidade - saidaDeProduto.getQuantidade());
                em.persist(estoqueMerged);
            });
            em.flush();
            if(arrayUtil.validaArray(validacaoException.get().getValidacoes())){
                return ResponseBuilder.respondeOkWithAlert(saidaDeProduto, validacaoException.get());
            }else{
                return ResponseBuilder.responseOk(saidaDeProduto);
            }
    }




    public void parseDTOtoModel(SaidaDeProduto saidaDeProduto, SaidaDeProdutoDTO saidaDeProdutoDTO) {
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto.getFuncionario(),saidaDeProdutoDTO.getFuncionario());
        saidaDeProdutoDTO.getProduto().forEach(produtoDTO -> {
            Produto produto = produtoService.getOneProduct(produtoDTO.getUuid());
            fieldUtil.updateFieldsDtoToModel(produto,produtoDTO);
            Categoria categoria = em.merge(produto.getCategoria());
            Produto produtoMerged = em.merge(produto);
            produto.setCategoria(categoria);
            saidaDeProduto.getProduto().add(produtoMerged);
        });

        Funcionario funcionario = em.merge(saidaDeProduto.getFuncionario());
        fieldUtil.updateFieldsDtoToModel(saidaDeProduto,saidaDeProdutoDTO);
        saidaDeProduto.setFuncionario(funcionario);
    }
    @Transactional
    public Response update(String uuid,String json) {
        AtomicReference<ValidacaoException> validacaoException = null;
            SaidaDeProdutoDTO saidaDeProdutoDTO = gson.fromJson(json, SaidaDeProdutoDTO.class);
            validaSaidaDeProduto(saidaDeProdutoDTO);
            Optional<SaidaDeProduto> saidaDeProdutoOp = SaidaDeProduto.findByIdOptional(uuid);
            if(saidaDeProdutoOp.isPresent()){
                saidaDeProdutoOp.get().getProduto().forEach(produto -> {
                    Estoque estoque = estoqueService.findByProduct(produto);
                    validacaoException.set(estoque.validaQuantidade(saidaDeProdutoOp.get()));
                });
                parseDTOtoModel(saidaDeProdutoOp.get(),saidaDeProdutoDTO);
                em.persist(saidaDeProdutoOp.get());
                if(ArrayUtil.validaArray(validacaoException.get().getValidacoes())){
                    return ResponseBuilder.respondeOkWithAlert(saidaDeProdutoOp.get(), validacaoException.get());
                }else{
                    return ResponseBuilder.responseOk(saidaDeProdutoOp.get());
                }
            }else{
                return ResponseBuilder.responseEntityNotFound();
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
        saidaDeProdutoDTO.getProduto().forEach(produtoDTO -> {
            produtoService.validaProduto(produtoDTO,validacao);
        });

        validacao.lancaErro();

    }
}
