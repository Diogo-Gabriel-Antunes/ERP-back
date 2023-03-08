package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import java.time.LocalDate;

@Getter
@Setter
public class MotoristaDTO implements DTO {
    private String uuid;
    private Integer idade;
    private String nome;
    private String sobrenome;
    private String CPF;
    private String CNH;
    private String categoriaCNH;
    private LocalDate dataDeValidadeCNH;
    private String telefone;
    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;
    private String email;
}
