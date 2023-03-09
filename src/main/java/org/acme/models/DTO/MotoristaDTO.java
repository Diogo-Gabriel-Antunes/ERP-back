package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import java.time.LocalDate;

@Getter
@Setter
public class MotoristaDTO implements DTO {
    private String uuid;
    private Integer idade;
    @LabelForm("Nome")
    private String nome;

    @LabelForm("Sobrenome")
    private String sobrenome;
    @LabelForm("CPF")
    private String CPF;
    @LabelForm("CNH")
    private String CNH;
    @LabelForm("Categoria CNH")
    private String categoriaCNH;
    private LocalDate dataDeValidadeCNH;
    private String telefone;
    @Type(EnderecoNFE.class)
    @LabelForm("Endereço")
    private EnderecoNFEDTO endereco;
    private String email;
}
