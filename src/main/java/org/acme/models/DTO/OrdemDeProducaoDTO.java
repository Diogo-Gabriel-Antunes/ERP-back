package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.OrdemDeProducao;
import org.acme.models.Product;
import org.acme.models.Transportadora;
import org.acme.models.enums.StatusDaProducao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
public class OrdemDeProducaoDTO implements DTO {
    private Product product;
    private Long quantidade;
    private StatusDaProducao status;
    private LocalDate inicioDaProducao;
    private LocalDate finalizadoEm;

    public static OrdemDeProducaoDTO convert(OrdemDeProducao ordemDeProducao) {
        OrdemDeProducaoDTO ordemDeProducaoDTO = new OrdemDeProducaoDTO();
        FieldUtil fieldUtil = new FieldUtil();
        ordemDeProducao.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(ordemDeProducao,ordemDeProducaoDTO);
        return ordemDeProducaoDTO;
    }
}
