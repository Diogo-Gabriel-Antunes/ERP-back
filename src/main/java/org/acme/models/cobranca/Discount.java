package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
@Getter
@Setter
@Entity
public class Discount {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double value;
    private long dueDateLimitDays;
    @Enumerated(EnumType.STRING)
    private DescontType descontType;
    @OneToOne
    @JsonbTransient
    private CobrancaParcelada cobrancaParcelada;
    @OneToOne
    @JsonbTransient
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;
}
