package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.DTO.Financas.DiscountDTO;
import org.acme.models.DTO.Financas.FineDTO;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.models.DTO.Financas.SplitDTO;
import org.acme.models.Model;
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
    @Type(Split.class)
    private SplitDTO split;
    @Type(Discount.class)
    private DiscountDTO discount;
    @Type(Interest.class)
    private InterestDTO interest;
    @Type(Fine.class)
    private FineDTO fine;
    private SubscriptionCycle cycle;
}
