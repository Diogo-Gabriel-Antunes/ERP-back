package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.OrdemDeProducao;
import org.acme.models.Product;
import org.acme.models.TimesOrdemDeProducao;
import org.acme.models.Transportadora;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrdemDeProducaoDTO implements DTO {

    private Product product;
    private Long quantidade;
    private String descricao;
    private StatusDaProducao status;
    private List<TimesOrdemDeProducaoDTO> atualizadoEm;
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
