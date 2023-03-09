package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Contato;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;


@Getter
@Setter
public class LojaDTO implements DTO{
    private String nomeLoja;
    private String cnpj;
    private String razaoSocial;
    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;
    @Type(Contato.class)
    private ContatoDTO contato;

}
