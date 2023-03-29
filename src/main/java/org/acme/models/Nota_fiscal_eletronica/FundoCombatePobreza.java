package org.acme.models.Nota_fiscal_eletronica;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class FundoCombatePobreza extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double valor;
    @OneToOne
    private ICMS icms;
    @OneToOne
    private SubstituicaoTributaria substituicaoTributaria;
}