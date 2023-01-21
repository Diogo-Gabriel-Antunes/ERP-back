package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;
import org.acme.models.Nota_fiscal_eletronica.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class BaseCalculo{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Enumerated(EnumType.STRING)
    private ModalidadeDeterminacao modalidadeDeterminacao;
    private double valor;
    private double percentualReducao;
    @Enumerated(EnumType.STRING)
    private TipoDaBaseDeCalculo tipoDaBaseDeCalculo;
    @OneToMany(mappedBy = "baseCalculo")
    private List<Cofins> cofins;
    @OneToMany(mappedBy = "baseCalculo")
    private List<SubstituicaoTributaria> substituicaoTributarias;
    @OneToMany(mappedBy = "baseCalculo")
    private List<FundoCombatePobreza> fundoCombatePobrezas;
    @OneToMany(mappedBy = "baseCalculo")
    private List<Imposto> cides;
    @OneToMany(mappedBy = "baseCalculo")
    private List<ICMS> icms;

}

enum TipoDaBaseDeCalculo{
    Cofins,SubstituicaoTributaria,FundoCombatePobreza,Cide,ICMS;
}