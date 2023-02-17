package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Funcionario;
import org.acme.models.Produto;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
public class SaidaDeProdutoDTO implements DTO  {
    private LocalDateTime dataDaSaida;
    @Type(Produto.class)
    private ProdutoDTO produto;
    private int quantidade;
    private LocalDateTime dataDeCriacao;
    @Type(Funcionario.class)
    private FuncionarioDTO retirante;
    private LocalDateTime dataDeEdicao;
}
