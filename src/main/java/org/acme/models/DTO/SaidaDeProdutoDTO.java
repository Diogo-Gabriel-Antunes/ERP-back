package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SaidaDeProdutoDTO implements DTO {
    private String uuid;
    @LabelForm("Data da saida")
    private LocalDateTime dataDaSaida;
    @Type(List.class)
    @LabelForm("Produtos")
    private List<ProdutoDTO> produto;
    private int quantidade;
    private LocalDateTime dataDeCriacao;
    @Type(Funcionario.class)
    @LabelForm("Responsavel")
    private FuncionarioDTO funcionario;
    private LocalDateTime dataDeEdicao;
}

