package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IPIDTO extends ImpostoDTO {
    private String cnpjProdutor;
    private SeloControleDTO seloControle;
    private String codigoEnquadramentoLegal;
    private UnidadeNFEDto unidade;
    private double valor;
}
