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
public class BaseCalculo extends PanacheEntityBase implements Model {
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



}

