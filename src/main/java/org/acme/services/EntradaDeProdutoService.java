package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.JsonUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.EntradaDeProdutoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@ApplicationScoped
public class EntradaDeProdutoService extends Service {

    @Transactional
    public Response create(String json) {
        try {

            EntradaDeProdutoDTO entradaDeProdutoDTO = gson.fromJson(json, EntradaDeProdutoDTO.class);
            EntradaDeProduto entradaDeProduto = new EntradaDeProduto();
            validaEntradaDeProduto(entradaDeProdutoDTO);
            convertDTO(entradaDeProduto,entradaDeProdutoDTO);
            EntradaDeProduto entradaDeProdutoMerged= em.merge(entradaDeProduto);
            Estoque estoque = estoqueService.findByProduct(entradaDeProduto.getProduto());
            em.persist(estoque);
            estoque.setUltimaAtualizacao(LocalDateTime.now());
            estoque.setQuantidade(estoque.getQuantidade() + entradaDeProdutoMerged.getQuantidade());
            entradaDeProduto.persist();
            em.flush();
            return ResponseBuilder.responseOk(entradaDeProdutoMerged);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();

        }

    }

    private void convertDTO(EntradaDeProduto entradaDeProduto,EntradaDeProdutoDTO entradaDeProdutoDTO) {
        Produto product = produtoService.getOneProduct(entradaDeProdutoDTO.getProduto().getUuid());
        Funcionario responsavel = Funcionario.findById(entradaDeProdutoDTO.getResponsavel().getUuid());
        Fornecedor fornecedor = fornecedorService.getOne(entradaDeProdutoDTO.getFornecedor().getUuid());
        em.persist(fornecedor);
        em.persist(responsavel);
        em.persist(product);
        fieldUtil.updateFieldsDtoToModel(entradaDeProduto,entradaDeProdutoDTO);
        entradaDeProduto.setProduto(product);
        entradaDeProduto.setResponsavel(responsavel);
        entradaDeProduto.setFornecedor(fornecedor);
    }

    private void validaEntradaDeProduto(EntradaDeProdutoDTO entradaDeProdutoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if (entradaDeProdutoDTO.getProduto() != null) {
            if (entradaDeProdutoDTO.getProduto().isStatus()) {
                validacao.add("Produto esta bloqueado no sistema");
            }
        } else {
            validacao.add("O produto esta invalido");
        }
        if (entradaDeProdutoDTO.getFornecedor() == null) {
            validacao.add("O fornecedor do produto deve ser informado");
        }
        if (entradaDeProdutoDTO.getQuantidade() <= 0) {
            validacao.add("A quantiadade deve ser informada");
        }
        if (entradaDeProdutoDTO.getResponsavel() == null) {
            validacao.add("O responsavel deve ser informado");
        }
        if (entradaDeProdutoDTO.getTipoDeMovimentacao() == null) {
            validacao.add("O tipo da movimentação deve ser informado");
        }

        validacao.lancaErro();
    }

    @Transactional
    public Response update(String uuid, String json) {
        try {
            EntradaDeProdutoDTO entradaDeProdutoDTO = gson.fromJson(json, EntradaDeProdutoDTO.class);
            validaEntradaDeProduto(entradaDeProdutoDTO);
            EntradaDeProduto entradaDeProduto = EntradaDeProduto.findById(uuid);
            Estoque estoque = estoqueService.findByProduct(entradaDeProduto.getProduto());
            em.persist(estoque);
            em.persist(entradaDeProduto);
            if(entradaDeProdutoDTO.getQuantidade() != entradaDeProduto.getQuantidade()){
                int novaQuantidade =   entradaDeProdutoDTO.getQuantidade() - entradaDeProduto.getQuantidade();
                estoque.setQuantidade(estoque.getQuantidade() + novaQuantidade);
            }
            convertDTO(entradaDeProduto,entradaDeProdutoDTO);
            EntradaDeProduto entradaDeProdutoMerged = em.merge(entradaDeProduto);
            em.flush();
            return ResponseBuilder.responseOk(entradaDeProdutoMerged);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();

        }
    }
}
