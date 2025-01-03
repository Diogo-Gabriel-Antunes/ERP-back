package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.asaas.DescontType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class DiscountDTO implements DTO {
    private double value;
    private long dueDateLimitDays;
    @Enumerated(EnumType.STRING)
    private DescontType descontType;
}

