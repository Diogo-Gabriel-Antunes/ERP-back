package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssqnDTO extends ImpostoDTO {
    private String codigoServico;
    private String valorDeducao;
    private double valorOutros;
    private double descontoIncondicionado;
    private double decontoCondicionado;
    private double valorRetencaoIss;
    private String codigoMunicipalServico;
    private String codigoMunicipioIncidencia;
    private String codigoMunicipioFatoGerador;
    private String codigoExigibilidade;
    private String numeroProcessoSuspensao;
}
