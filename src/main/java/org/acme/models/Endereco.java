package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Endereco extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String xEnder;
    private String xLgr;
    private String nro;
    private String xCpl;
    private String xBairro;
    private String cMun;
    private String xMun;
    private String uf;
    private String CEP;
    private String cPais;
    private String xPais;
    @OneToOne(mappedBy = "endereco")
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    private Transportadora transportadora;
    @OneToOne(mappedBy = "endereco")
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    private Cliente cliente;
}
