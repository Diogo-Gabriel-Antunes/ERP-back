package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.ArrayUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Cliente;
import org.acme.models.DTO.EstoqueExternoDTO;
import org.acme.models.EstoqueExterno;
import org.acme.models.Funcionario;
import org.acme.models.ItensExternos;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@ApplicationScoped
public class EstoqueExternoService extends Service implements ServiceInterface {
    @Override
    @Transactional
    public Response create(String json) {
        try {
            EstoqueExternoDTO estoqueExternoDTO = gson.fromJson(json, EstoqueExternoDTO.class);
            validaDTO(estoqueExternoDTO);
            EstoqueExterno estoqueExterno = new EstoqueExterno();
            convertDTO(estoqueExterno, estoqueExternoDTO);
            em.persist(estoqueExterno);
            return ResponseBuilder.responseOk(estoqueExterno);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    @Override
    @Transactional
    public Response update(String uuid, String json) {
        try {
            EstoqueExternoDTO estoqueExternoDTO = gson.fromJson(json, EstoqueExternoDTO.class);
            validaDTO(estoqueExternoDTO);
            EstoqueExterno estoqueExterno = EstoqueExterno.findById(uuid);
            if (estoqueExterno != null) {
                convertDTO(estoqueExterno, estoqueExternoDTO);
                em.persist(estoqueExterno);
                return ResponseBuilder.responseOk(estoqueExterno);
            } else {
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

    public void convertDTO(EstoqueExterno estoqueExterno, EstoqueExternoDTO estoqueExternoDTO) {
        fieldUtil.updateFieldsDtoToModel(estoqueExterno, estoqueExternoDTO);
        estoqueExterno.setItens(new HashSet<>());
        Funcionario responsavel = Funcionario.findById(estoqueExternoDTO.getResponsavel().getUuid());
        Cliente cliente = Cliente.findById(estoqueExternoDTO.getCliente().getUuid());
        estoqueExternoDTO.getItens().forEach(item -> {
            ItensExternos itemBD = ItensExternos.findById(item.getUuid());
            estoqueExterno.getItens().add(itemBD);
        });
        estoqueExterno.setCliente(cliente);
        estoqueExterno.setResponsavel(responsavel);
    }

    public void validaDTO(EstoqueExternoDTO estoqueExternoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(estoqueExternoDTO.getItens() != null){
            if (estoqueExternoDTO.getItens().isEmpty()) {
                validacao.add("É necessario informar ao menos um produto");
            }
        }else{
            validacao.add("É necessario informar ao menos um produto");
        }
        if (estoqueExternoDTO.getCliente() == null) {
            validacao.add("É necessario informar o cliente do estoque");
        }
        if (estoqueExternoDTO.getResponsavel() == null) {
            validacao.add("É necessario informar o responsavel pelo estoque");
        }

        validacao.lancaErro();
    }
}
