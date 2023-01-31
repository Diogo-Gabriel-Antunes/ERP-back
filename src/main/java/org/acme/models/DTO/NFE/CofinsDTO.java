package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CofinsDTO extends ImpostoDTO {
    private long quantidadeVendida;
    private double aliquotaReais;
}
