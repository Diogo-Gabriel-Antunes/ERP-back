package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.PrimitiveUtil.LongUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.DTO.MontagemDeCargaDTO;
import org.acme.models.consulta.PreparacaoDeCargaPreview;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class PreparacaoDeCargaService extends Service implements ServiceInterface {

    @Override
    public Response create(String json) {
            MontagemDeCargaDTO montagemDeCargaDTO = gson.fromJson(json, MontagemDeCargaDTO.class);
            Long capacidade = montagemDeCargaDTO.getVeiculo().getLotacaoMaxima();
            Long tamanhoMedioDosProdutos = 0L;
            Double fatorOcupacao = 1.2;
            for (ItensDTO iten : montagemDeCargaDTO.getItens()) {
                tamanhoMedioDosProdutos += Long.parseLong(String.valueOf(iten.getProduto().getPesoCubico()));
            }
            tamanhoMedioDosProdutos = tamanhoMedioDosProdutos / montagemDeCargaDTO.getItens().size();
            Long capacidadeTotalDeProdutos = capacidade / tamanhoMedioDosProdutos;

            PreparacaoDeCargaPreview preparacaoDeCargaPreview = new PreparacaoDeCargaPreview();
            preparacaoDeCargaPreview.setCapacidadeVeiculo(capacidade);
            preparacaoDeCargaPreview.setQuantidadeDeItens(LongUtil.parseFromInteger(montagemDeCargaDTO.getItens().size()));
            preparacaoDeCargaPreview.setFatorOcupacao(fatorOcupacao);
            preparacaoDeCargaPreview.setTamanhoMedioDosProdutos(tamanhoMedioDosProdutos);
            preparacaoDeCargaPreview.setCapacidadeTotalDeProdutos(capacidadeTotalDeProdutos);
            preparacaoDeCargaPreview.setMontagemDeCargaDTO(montagemDeCargaDTO);
            return ResponseBuilder.responseOk(preparacaoDeCargaPreview);
    }

    @Override
    public Response update(String uuid, String json) {
        return null;
    }
}

