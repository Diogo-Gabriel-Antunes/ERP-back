package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.asaas.SubscriptionCycle;
import org.acme.models.asaas.*;

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
