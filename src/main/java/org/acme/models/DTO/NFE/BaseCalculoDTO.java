package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Nota_fiscal_eletronica.ModalidadeDeterminacao;
import org.acme.models.Nota_fiscal_eletronica.TipoDaBaseDeCalculo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class BaseCalculoDTO extends ImpostoDTO implements DTO {
    private ModalidadeDeterminacao modalidadeDeterminacao;
    private double percentualReducao;
    @Enumerated(EnumType.STRING)
    private TipoDaBaseDeCalculo tipoDaBaseDeCalculo;
}
