package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @ManyToOne
    private Discount discount;
    @ManyToOne
    private Interest interest;
    @ManyToOne
    private Fine fine;
    private boolean postalService;
    @ManyToMany
    @JoinTable(name="splits_parcelas", joinColumns=
            {@JoinColumn(name="cobranca_parcelada_id")}, inverseJoinColumns=
            {@JoinColumn(name="split_id")})
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Split> splits;
    @OneToOne
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;
}


