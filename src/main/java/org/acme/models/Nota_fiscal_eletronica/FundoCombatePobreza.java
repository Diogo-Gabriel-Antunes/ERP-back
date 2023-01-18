package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FundoCombatePobreza{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double valor;
    @ManyToOne
    private ICMS icms;
    @ManyToOne
    private SubstituicaoTributaria substituicaoTributaria;
}