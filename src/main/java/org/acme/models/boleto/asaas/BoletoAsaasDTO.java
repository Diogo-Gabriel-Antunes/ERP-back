package org.acme.models.boleto.asaas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class BoletoAsaasDTO implements DTO {

    private String uuid;
    private String name;
    private String description;
    private String endDate;
    private String value;
    private BillingType billingType;
    private ChargeType chargeType;
    private String dueDateLimitDays;
    private SubscriptionCycle subscriptionCycle;
    private String maxInstallmentCount;
    private String notificationEnabled;
}



