package org.acme.models.consulta;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.MontagemDeCargaDTO;

@Getter
@Setter
public class PreparacaoDeCargaPreview {
    private Long capacidadeVeiculo;
    private Long quantidadeDeItens;
    private Double fatorOcupacao;
    private Long tamanhoMedioDosProdutos;
    private Long capacidadeTotalDeProdutos;
    private MontagemDeCargaDTO montagemDeCargaDTO;
}
