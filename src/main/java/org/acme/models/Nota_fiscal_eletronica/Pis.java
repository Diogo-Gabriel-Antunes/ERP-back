package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Pis {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cst;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double aliquota;
    private double valor;
    private long quantidadeVendida;
    private double aliquotaReais;
    @ManyToOne
    private SubstituicaoTributaria substituicaoTributaria;
}
