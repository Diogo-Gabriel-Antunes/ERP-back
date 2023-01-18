package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class SubstituicaoTributaria{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double margemValorAdicionado;
    @OneToMany
    private List<FundoCombatePobreza> fundoCombatePobreza;
    @OneToMany(mappedBy = "substituicaoTributaria")
    private List<Cofins> cofins;
    @OneToMany(mappedBy = "substituicaoTributaria")
    private List<ICMS> icms;

}