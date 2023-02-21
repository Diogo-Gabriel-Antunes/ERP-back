package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.MateriaPrima;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.Produto;

import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class FornecedorDTO implements DTO{

    private String uuid;
    private String nomeDaEmpresa;
    private String email;
    private String nomeParaContato;
    private String telefone;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;
    @Type(Set.class)
    private Set<ProdutoDTO> produtos;
    @Type(Set.class)
    private Set<MateriaPrimaDTO> materiaPrimas;
}
