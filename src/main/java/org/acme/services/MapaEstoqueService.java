package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.JsonUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.DTO.MapaEstoqueDTO;
import org.acme.models.MapaEstoque;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class MapaEstoqueService extends Service {

    @Transactional
    public Response create(String json) {
        try {
            JsonUtil.preValidate(json, MapaEstoqueDTO.class);

            MapaEstoqueDTO mapaEstoqueDTO = gson.fromJson(json, MapaEstoqueDTO.class);
            validaMapaEstoque(mapaEstoqueDTO);
            MapaEstoque mapaEstoque = new MapaEstoque();
            convertDTO(mapaEstoque, mapaEstoqueDTO);
            em.persist(mapaEstoque);
            return ResponseBuilder.responseOk(mapaEstoque);
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
            MapaEstoqueDTO mapaEstoqueDTO = gson.fromJson(json, MapaEstoqueDTO.class);
            Optional<MapaEstoque> mapaEstoque = MapaEstoque.findByIdOptional(uuid);
            if (mapaEstoque.isPresent()) {
                convertDTO(mapaEstoque.get(), mapaEstoqueDTO);
                mapaEstoque.get().setUuid(uuid);
                em.persist(mapaEstoque.get());
                return ResponseBuilder.responseOk(mapaEstoque.get());
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

    private void convertDTO(MapaEstoque mapaEstoque, MapaEstoqueDTO mapaEstoqueDTO) {
        Produto produto = new Produto();
        fieldUtil.updateFieldsDtoToModel(produto, mapaEstoqueDTO.getProduto());
        fieldUtil.updateFieldsDtoToModel(mapaEstoque, mapaEstoqueDTO);
        mapaEstoque.setProduto(produto);
    }

    public void validaMapaEstoque(MapaEstoqueDTO mapaEstoqueDTO) {
        validaMapaEstoque(null, mapaEstoqueDTO);
    }

    public void validaMapaEstoque(ValidacaoException validacao, MapaEstoqueDTO mapaEstoqueDTO) {
        Boolean semValidacao = validacao == null;
        if (semValidacao) {
            validacao = new ValidacaoException();
        }
        if (!StringUtil.stringValida(mapaEstoqueDTO.getIdentificacao())) {
            validacao.add("Endereço no estoque deve ser informado");
        }
        if (!StringUtil.stringValida(mapaEstoqueDTO.getLocalPosicao())) {
            validacao.add("A posição deve ser informada");
        }
        if (!StringUtil.stringValida(mapaEstoqueDTO.getTipoLocal())) {
            validacao.add("O tipo local deve ser informado");
        }
        if (semValidacao) {
            validacao.lancaErro();
        }
    }
}
