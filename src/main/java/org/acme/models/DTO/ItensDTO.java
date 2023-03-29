package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Cliente;
import org.acme.models.Produto;
import org.acme.models.enums.PrioridadeCarga;

@Getter
@Setter
public class ItensDTO implements DTO {
    private String uuid;
    @Type(Produto.class)
    @LabelForm("Produtos")
    private ProdutoDTO produto;
    @LabelForm("Cliente")
    @Type(Cliente.class)
    private ClienteDTO cliente;
    @LabelForm("Quantidade")
    private Long quantidade;
    private PrioridadeCarga prioridade;

}
