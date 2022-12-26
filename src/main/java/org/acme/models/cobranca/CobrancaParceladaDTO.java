package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.DTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private Discount discount;
    private Interest interest;
    private Fine fine;
    private boolean postalService;
    private List<Split> splits;
    private CobrancaParceladaRetorno cobrancaParceladaRetorno;

    public CobrancaParcelada convertToModel() {
        CobrancaParcelada cobrancaParcelada = new CobrancaParcelada();
        FieldUtil fieldUtil = new FieldUtil();
        fieldUtil.updateFieldsDtoToModel(cobrancaParcelada,this);
        return cobrancaParcelada;
    }
}
