package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.Nota_fiscal_eletronica.Pessoa;
import org.acme.models.Unidade;

import java.time.LocalDate;

@Getter
@Setter
public class UnidadeDTO implements DTO{
    private Pessoa pessoa;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;

    public static UnidadeDTO convert(Unidade unidade) {
        UnidadeDTO unidadeDTO = new UnidadeDTO();
        FieldUtil fieldUtil = new FieldUtil();
        unidade.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(unidade,unidadeDTO);
        return unidadeDTO;
    }
}
