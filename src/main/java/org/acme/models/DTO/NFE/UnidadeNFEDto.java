package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;

@Getter
@Setter
public class UnidadeNFEDto implements DTO {
    private String comercial;
    private String tributavel;
    private long quantidade;
}
