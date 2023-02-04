package org.acme.models.cobranca.Assinatura.Boleto;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;
import org.acme.models.cobranca.ChargeType;
import org.acme.models.cobranca.SubscriptionCycle;
import org.acme.models.cobranca.BillingType;

@Getter
@Setter
public class BoletoAsaasDTO implements DTO {

    private String name;
    private String description;
    private String endDate;
    private String value;
    private BillingType billingType;
    private ChargeType chargeType;
    private String dueDateLimitDays;
    private SubscriptionCycle subscriptionCycle;
    private float maxInstallmentCount;
    private String notificationEnabled;
    private RetornoAsaas retornoAsaas;
    private String atualizadoEm;
}



