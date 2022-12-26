package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CobrancaParcelada implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String customer;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    private double value;
    private String dueDate;
    private String description;
    private String externalReference;
    private Integer installmentCount;
    private Double installmentValue;
    @OneToOne(mappedBy = "cobrancaParcelada")
    private Discount discount;
    @OneToOne(mappedBy = "cobrancaParcelada")
    private Interest interest;
    @OneToOne(mappedBy = "cobrancaParcelada")
    private Fine fine;
    private boolean postalService;
    @OneToMany(mappedBy = "cobrancaParcelada")
    private List<Split> splits;
    @OneToOne
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;
}


