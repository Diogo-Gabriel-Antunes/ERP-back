package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.VeiculoDTO;
import org.acme.models.Nota_fiscal_eletronica.Cor;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class VeiculoService extends Service {


    public Response listAll() {
        try {
            List<Veiculo> veiculos = em.createQuery("select v from Veiculo v", Veiculo.class)
                    .getResultList();
            return Response.ok(veiculos).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


    }

    @Transactional
    public Response create(String json) {
        try {
            VeiculoDTO veiculoDTO = gson.fromJson(json, VeiculoDTO.class);
            validaDTO(veiculoDTO);
            Veiculo veiculo = new Veiculo();
            convertDTOtoModel(veiculo,veiculoDTO);
            veiculo.persistAndFlush();
            return ResponseBuilder.responseOk(veiculo);
        } catch (JsonSyntaxException j) {
            j.printStackTrace();
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }
    @Transactional
    public Response update(String uuid,String json) {
        try {
            VeiculoDTO veiculoDTO = gson.fromJson(json, VeiculoDTO.class);
            validaDTO(veiculoDTO);
            Veiculo veiculo = Veiculo.findById(uuid);
            convertDTOtoModel(veiculo,veiculoDTO);
            veiculo.persistAndFlush();
            return ResponseBuilder.responseOk(veiculo);
        } catch (JsonSyntaxException j) {
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }


    private void convertDTOtoModel(Veiculo veiculo, VeiculoDTO veiculoDTO) {
        fieldUtil.updateFieldsDtoToModel(veiculo,veiculoDTO);
        if(veiculoDTO.getCor() != null){
            Cor cor = Cor.findById(veiculoDTO.getCor().getUuid());
            veiculo.setCor(cor);
        }
    }
    public void validaDTO(VeiculoDTO veiculoDTO) {
        validaDTO(null, veiculoDTO);
    }

    public void validaDTO(ValidacaoException validacao, VeiculoDTO veiculoDTO) {
        Boolean naoExisteValidacao = validacao == null;
        if (naoExisteValidacao) {
            validacao = new ValidacaoException();
        }

        if (!StringUtil.stringValida(veiculoDTO.getPlaca())) {
            validacao.add("É necessario adicionar a placa do veiculo");
        }
        if (veiculoDTO.getAnoFabricacao() == null || veiculoDTO.getAnoFabricacao() <= 0 ) {
            validacao.add("É necessario adicionar o ano de fabricação");
        }
        if (veiculoDTO.getAnoModelo() == null || veiculoDTO.getAnoModelo() <= 0) {
            validacao.add("É necessario adicionar o ano do modelo");
        }
        if (!StringUtil.stringValida(veiculoDTO.getChassi())) {
            validacao.add("É necessario adicionar o chassi");
        }
        if (!StringUtil.stringValida(veiculoDTO.getCodigoModelo())) {
            validacao.add("É necessario adicionar o codigo do modelo");
        }
        if (veiculoDTO.getLotacaoMaxima() == null || veiculoDTO.getLotacaoMaxima() <= 0) {
            validacao.add("É necessario adicionar a lotacao maxima");
        }

        if(naoExisteValidacao){
            validacao.lancaErro();
        }
    }
}

