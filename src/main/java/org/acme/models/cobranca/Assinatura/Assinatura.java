package org.acme.models.cobranca.Assinatura;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
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
    @Type(String.class)
    private String customer;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    @Type(double.class)
    private double value;
    @Type(String.class)
    private String nextDueDate;
    @Type(String.class)
    private String description;
    @Type(String.class)
    private String endDate;
    @Type(String.class)
    private String maxPayments;
    @Type(String.class)
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

    public Assinatura() {
        this.split = new Split();
        this.discount = new Discount();
        this.interest = new Interest();
        this.fine = new Fine();
    }
}
