package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

@Getter
@Setter
public class TransportadorDTO implements DTO{

    private String uuid;
    @LabelForm("CNPJ/CPF")
    private String cnpjCpf;
    private String razaoSocial;
    @LabelForm("Nome")
    private String nome;
    @LabelForm("Inscrição estadual")
    private String inscricaoEstadual;
    @Type(EnderecoNFE.class)
    @LabelForm("Endereço")
    private EnderecoNFEDTO endereco;


}
