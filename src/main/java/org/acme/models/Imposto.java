package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.Nota_fiscal_eletronica.BaseCalculo;
import org.acme.models.Nota_fiscal_eletronica.FundoCombatePobreza;
import org.acme.models.Nota_fiscal_eletronica.SubstituicaoTributaria;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoImposto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Imposto extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Enumerated(EnumType.STRING)
    private TipoImposto tipoImposto;
    private double valor;
    private String cst;
    private double aliquota;
    @OneToOne
    private BaseCalculo baseCalculo;
    @OneToOne
    private SubstituicaoTributaria substituicaoTributaria;
    @OneToOne
    private FundoCombatePobreza fundoCombatePobreza;
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
