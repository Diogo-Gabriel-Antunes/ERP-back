package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;
import org.acme.models.cobranca.DescontType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class SplitDTO implements DTO {
    private String walletId;
    private double fixedValue;
    private double percentualValue;
}

