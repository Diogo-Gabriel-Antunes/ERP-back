package org.acme.models.cobranca;


import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CobrancaParceladaRetorno {
    private String object;
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    private String dateCreated;
    private String customer;
    private String paymentLink ;
    private float value;
    private float netValue;
    private String originalValue ;
    private String interestValue;
    private String description;
    private boolean canBePaidAfterDueDate;
    private String pixTransaction;
    private String status;
    private String dueDate;
    private String originalDueDate;
    private String paymentDate ;
    private String clientPaymentDate ;
    private String installmentNumber ;
    private String invoiceUrl;
    private String invoiceNumber;
    private String externalReference;
    private boolean deleted;
    private boolean anticipated;
    private boolean anticipable;
    private String creditDate ;
    private String estimatedCreditDate ;
    private String transactionReceiptUrl ;
    private String nossoNumero;
    private String bankSlipUrl;
    private String lastInvoiceViewedDate;
    private boolean postalService;
    private String lastBankSlipViewedDate;
    @ManyToOne
    private Discount DiscountObject;
    @ManyToOne
    private Fine FineObject;
    @ManyToOne
    private Interest InterestObject;
    @ManyToOne
    private Refunds refunds;
    @ManyToOne
    @JsonbTransient
    private CobrancaParcelada cobrancaParcelada;
}
