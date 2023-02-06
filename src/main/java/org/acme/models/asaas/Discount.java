package org.acme.models.asaas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.asaas.Assinatura.Assinatura;
import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Discount implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double value;
    private long dueDateLimitDays;
    @Enumerated(EnumType.STRING)
    private DescontType descontType;
    @OneToMany(mappedBy = "discount")
    @JsonbTransient
    private List<CobrancaParcelada> cobrancaParcelada;
    @OneToMany(mappedBy = "DiscountObject")
    @JsonbTransient
    private List<CobrancaParceladaRetorno> cobrancaParceladaRetorno;
    @JsonbTransient
    @OneToMany(mappedBy = "discount")
    private List<Assinatura> assinatura;
}
