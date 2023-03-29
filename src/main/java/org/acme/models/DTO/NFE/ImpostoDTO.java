package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoImposto;

import javax.json.bind.annotation.JsonbTransient;

@Getter
@Setter
public class ImpostoDTO implements DTO {
    private TipoImposto tipoImposto;
    private double valor;
    private String cst;
    private double aliquota;
    BaseCalculoDTO baseCalculo;
    SubstituicaoTributariaDTO substituicaoTributaria;
    @JsonbTransient
    FundoCombatePobrezaDTO fundoCombatePobreza;
    private Estado estado;
}
