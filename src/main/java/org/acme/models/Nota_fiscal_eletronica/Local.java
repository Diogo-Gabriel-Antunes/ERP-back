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
public class Local {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cpfCnpj;
    private String razaoSocial;
    @OneToOne
    private Telefone telefone;
    private String email;
    private String inscricaoEstadual;
    @OneToOne
    private EnderecoNFE enderecoNFE;
}
