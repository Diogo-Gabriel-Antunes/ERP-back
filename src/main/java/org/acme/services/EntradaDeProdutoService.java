package org.acme.services;

import com.google.gson.JsonSyntaxException;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.EntradaDeProdutoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class EntradaDeProdutoService extends Service {


    public Response create(String json) {
        try {
            EntradaDeProdutoDTO entradaDeProdutoDTO = gson.fromJson(json, EntradaDeProdutoDTO.class);
            EntradaDeProduto entradaDeProduto = new EntradaDeProduto();
            validaEntradaDeProduto(entradaDeProdutoDTO);
            entradaDeProduto.persist();
            convertDTO(entradaDeProduto,entradaDeProdutoDTO);
            EntradaDeProduto entradaDeProdutoMerged= em.merge(entradaDeProduto);
            Estoque estoque = estoqueService.findByProduct(entradaDeProduto.getProduto());
            em.persist(estoque);
            estoque.setQuantidade(estoque.getQuantidade() + entradaDeProdutoMerged.getQuantidade());
            em.flush();
            return ResponseBuilder.responseOk(entradaDeProdutoMerged);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnNumberFormat();
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
        Cliente fornecedor = (Cliente) clienteService.findOne(entradaDeProduto.getUuid()).getEntity();
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
            return ResponseBuilder.returnNumberFormat();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();

        }
    }
}
