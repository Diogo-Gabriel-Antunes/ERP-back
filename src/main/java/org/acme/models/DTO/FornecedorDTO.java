package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import java.util.Set;

@Getter
@Setter
public class FornecedorDTO implements DTO{

    private String uuid;
    @LabelForm("Nome da empresa")
    private String nomeDaEmpresa;
    private String email;
    @LabelForm("Nome para contato")
    private String nomeParaContato;
    private String telefone;
    @LabelForm("Inscrição estadual")
    private String inscricaoEstadual;
    @LabelForm("Inscrição municipal")
    private String inscricaoMunicipal;
    @Type(EnderecoNFE.class)
    @LabelForm("Endereço")
    private EnderecoNFEDTO endereco;
    @Type(Set.class)
    private Set<ProdutoDTO> produtos;
    @Type(Set.class)
    private Set<MateriaPrimaDTO> materiaPrimas;
}
