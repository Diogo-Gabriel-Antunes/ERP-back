package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Getter
@Setter
@Entity
public class Interest {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double value;
    @OneToOne
    @JsonbTransient
    private CobrancaParcelada cobrancaParcelada;
    @OneToOne
    @JsonbTransient
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;
}
