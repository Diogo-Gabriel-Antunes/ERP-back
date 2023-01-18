package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Destinatario {
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
    private Integer regimeTributario;
    private Integer regimeTributarioEspecial;
    private boolean simplesNacional;
    @OneToOne
    private Telefone telefone;
    private boolean orgaoPublico;
    private Integer indicadorInscricaoEstadual;
    private String codigoEstrangeiro;
}
