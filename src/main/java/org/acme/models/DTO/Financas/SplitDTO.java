package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;

@Getter
@Setter
public class SplitDTO implements DTO {
    private String walletId;
    private double fixedValue;
    private double percentualValue;
}

