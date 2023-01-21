package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.BaseCalculo;
import org.acme.models.Nota_fiscal_eletronica.SubstituicaoTributaria;
import org.acme.models.enums.TipoImposto;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Imposto extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Enumerated(EnumType.STRING)
    private TipoImposto tipoImposto;
    private double valor;
    private String cst;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    @ManyToOne
    private SubstituicaoTributaria substituicaoTributaria;
}
