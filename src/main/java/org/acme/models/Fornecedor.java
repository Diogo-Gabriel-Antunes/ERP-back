package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Fornecedor extends PanacheEntityBase implements Model {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nomeDaEmpresa;
    private String email;
    private String nomeParaContato;
    private String telefone;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private EnderecoNFE endereco;
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Produto> produtos;
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<MateriaPrima> materiaPrimas;

    public Fornecedor() {
        endereco = new EnderecoNFE();
        produtos = new HashSet<>();
        materiaPrimas = new HashSet<>();
    }
}
