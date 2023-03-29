package org.acme.models.asaas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.DTO.Financas.DiscountDTO;
import org.acme.models.DTO.Financas.FineDTO;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.models.DTO.Financas.SplitDTO;

import java.util.List;

@Getter
@Setter
public class CobrancaParceladaDTO implements DTO {

    private String customer;
    private BillingType billingType;
    private double value;
    private String dueDate;
    private String description;
    private String externalReference;
    private Integer installmentCount;
    private Double installmentValue;
    private DiscountDTO discount;
    private InterestDTO interest;
    private FineDTO fine;
    private boolean postalService;
    private List<SplitDTO> splits;
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;


}
