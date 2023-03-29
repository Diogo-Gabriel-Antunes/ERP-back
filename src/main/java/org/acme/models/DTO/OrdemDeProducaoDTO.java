package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Produto;
import org.acme.models.enums.StatusDaProducao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrdemDeProducaoDTO implements DTO {

    @LabelForm("Produto")
    @Type(Produto.class)
    private ProdutoDTO product;
    @LabelForm("Quantidade")
    private Long quantidade;
    @LabelForm("Descrição")
    private String descricao;
    @LabelForm("Status da produção")
    private StatusDaProducao status;

    private List<TimesOrdemDeProducaoDTO> atualizadoEm;
    private LocalDate inicioDaProducao;
    private LocalDate finalizadoEm;

    public OrdemDeProducaoDTO() {
        atualizadoEm = new ArrayList<>();
    }


}
