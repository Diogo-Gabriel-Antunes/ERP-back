package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Cofins {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cst;
    private double aliquota;
    private double valor;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private long quantidadeVendida;
    private double aliquotaReais;
    @ManyToOne
    private SubstituicaoTributaria substituicaoTributaria;
}
