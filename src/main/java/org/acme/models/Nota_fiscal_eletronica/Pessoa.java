package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.RegimeTributario;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Pessoa {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cpfCnpj;
    @OneToOne
    private EnderecoNFE endereco;
    private String email;
    private boolean incentivadorCultural;
    private boolean incentivoFiscal;
    private String inscricaoEstadual;
    private String inscricaoSuframa;
    private String inscricaoMunicipal;
    private  String nomeFantasia;
    private String razaoSocial;
    // 1 = Simples nacional, 2 = Simples nacional - Excesso, 3 = regime normal
    private RegimeTributario regimeTributario;
    private RegimeTributaraioEspecial regimeTributarioEspecial;
    private boolean simplesNacional;
    @OneToOne
    private Telefone telefone;
    private boolean orgaoPublico;
    private Integer indicadorInscricaoEstadual;
    private String codigoEstrangeiro;
}
