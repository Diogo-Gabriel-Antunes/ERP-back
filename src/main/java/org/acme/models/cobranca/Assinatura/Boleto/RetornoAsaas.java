package org.acme.models.cobranca.Assinatura.Boleto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class RetornoAsaas {
    @Id
    private String id;
    private String name;
    private float value;
    private boolean active;
    private String chargeType;
    private String url;
    private String billingType;
    private String subscriptionCycle;
    private String description;
    private String endDate;
    private boolean deleted;
    private float viewCount;
    private float maxInstallmentCount;
    private float dueDateLimitDays;
    private String atualizadoEm;
    private boolean notificationEnabled;
    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private BoletoAsaas boletoAsaas;

}