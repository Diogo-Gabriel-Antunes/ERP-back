package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Contato;
import org.acme.models.Funcionario;
import org.acme.models.Itens;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


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
    @Type(List.class)
    private List<ItensDTO> itens;
}
