package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Fornecedor;
import org.acme.models.Funcionario;
import org.acme.models.Produto;
import org.acme.models.enums.TipoDeMovimentacao;

import java.time.LocalDateTime;

@Getter
@Setter
public class EntradaDeProdutoDTO implements DTO {
    private String uuid;
    @Type(Produto.class)
    @LabelForm("Produto")
    private ProdutoDTO produto;
    @LabelForm("Quantidade")
    private int quantidade;
    @Type(Fornecedor.class)
    @LabelForm("Fornecedor")
    private FornecedorDTO fornecedor;
    @LabelForm("Tipo da movimentação")
    private TipoDeMovimentacao tipoDeMovimentacao;
    @Type(Funcionario.class)
    @LabelForm("Responsavel")
    private FuncionarioDTO responsavel;
    private LocalDateTime dataDaMovientacao;
}
