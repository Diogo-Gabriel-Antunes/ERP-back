package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.Financas.DiscountDTO;
import org.acme.models.DTO.Financas.FineDTO;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.models.DTO.Financas.SplitDTO;
import org.acme.models.boleto.asaas.SubscriptionCycle;
import org.acme.models.cobranca.*;


import java.util.List;

@Getter
@Setter
public class AssinaturaDTO implements DTO {
    private String customer;
    private BillingType billingType;
    private double value;
    private String nextDueDate;
    private String description;
    private String endDate;
    private String maxPayments;
    private String externalReference;
    private SplitDTO split;
    private DiscountDTO discount;
    private InterestDTO interest;
    private FineDTO fine;
    private SubscriptionCycle cycle;
}
