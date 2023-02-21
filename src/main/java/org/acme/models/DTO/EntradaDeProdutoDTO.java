package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Cliente;
import org.acme.models.Funcionario;
import org.acme.models.Produto;
import org.acme.models.enums.TipoDeMovimentacao;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaDeProdutoDTO implements DTO{
    private String uuid;
    @Type(Produto.class)
    private ProdutoDTO produto;
    private int quantidade;
    @Type(Cliente.class)
    private ClienteDTO fornecedor;
    private TipoDeMovimentacao tipoDeMovimentacao;
    @Type(Funcionario.class)
    private FuncionarioDTO responsavel;
    private LocalDateTime dataDaMovientacao;
}
