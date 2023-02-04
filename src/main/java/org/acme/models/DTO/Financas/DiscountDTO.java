package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;
import org.acme.models.cobranca.DescontType;

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

