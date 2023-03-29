package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Nota_fiscal_eletronica.Pessoa;

import java.time.LocalDate;

@Getter
@Setter
public class UnidadeDTO implements DTO {
    private Pessoa pessoa;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;


}
