package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Funcionario;
import org.acme.models.Produto;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class SaidaDeProdutoDTO implements DTO  {
    private String uuid;
    private LocalDateTime dataDaSaida;
    @Type(List.class)
    private List<ProdutoDTO> produto;
    private int quantidade;
    private LocalDateTime dataDeCriacao;
    @Type(Funcionario.class)
    private FuncionarioDTO funcionario;
    private LocalDateTime dataDeEdicao;
}

