package org.acme.models.consulta;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.DTO.MontagemDeCargaDTO;
import org.acme.models.MontagemDeCarga;

@Getter
@Setter
public class PreparacaoDeCargaPreview implements DTO {
    private Long capacidadeVeiculo;
    private Long quantidadeDeItens;
    private Double fatorOcupacao;
    private Long tamanhoMedioDosProdutos;
    private Long capacidadeTotalDeProdutos;
    @Type(MontagemDeCarga.class)
    private MontagemDeCargaDTO montagemDeCargaDTO;
}
