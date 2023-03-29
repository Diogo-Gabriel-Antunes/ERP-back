package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Nota_fiscal_eletronica.BaseCalculo;

@Getter
@Setter
public class SubstituicaoTributariaDTO implements DTO {
    private double aliquota;
    private BaseCalculo baseCalculo;
    private double margemValorAdicionado;
}
