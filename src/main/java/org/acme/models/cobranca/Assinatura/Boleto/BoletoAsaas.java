package org.acme.models.cobranca.Assinatura.Boleto;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.acme.models.cobranca.ChargeType;
import org.acme.models.cobranca.SubscriptionCycle;
import org.acme.models.cobranca.BillingType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BoletoAsaas extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String name;
    private String description;
    private String endDate;
    private String value;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;
    private String dueDateLimitDays;
    @Enumerated(EnumType.STRING)
    private SubscriptionCycle subscriptionCycle;
    private float maxInstallmentCount;
    private String notificationEnabled;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private RetornoAsaas retornoAsaas;
    private String atualizadoEm;
}


