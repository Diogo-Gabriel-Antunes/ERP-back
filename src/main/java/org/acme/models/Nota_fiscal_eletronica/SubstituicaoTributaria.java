package org.acme.models.Nota_fiscal_eletronica;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;

import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class SubstituicaoTributaria extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double margemValorAdicionado;
    @OneToOne
    private FundoCombatePobreza fundoCombatePobreza;



}