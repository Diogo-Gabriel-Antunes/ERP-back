package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Getter
@Setter
@Entity
public class Split {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String walletId;
    private double fixedValue;
    private double percentualValue;
    @JsonbTransient
    @ManyToOne
    private CobrancaParcelada cobrancaParcelada;
    @JsonbTransient
    @ManyToOne
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;
}
