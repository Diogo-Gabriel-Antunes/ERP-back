package org.acme.models.cobranca.Assinatura;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.acme.models.boleto.asaas.SubscriptionCycle;
import org.acme.models.cobranca.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Assinatura extends PanacheEntityBase implements Model,ICobranca {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String customer;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    private double value;
    private String nextDueDate;
    private String description;
    private String endDate;
    private String maxPayments;
    private String externalReference;
    @ManyToOne
    private Split split;
    @ManyToOne
    private Discount discount;
    @ManyToOne
    private Interest interest;
    @ManyToOne
    private Fine fine;
    @Enumerated(EnumType.STRING)
    private SubscriptionCycle cycle;
}
