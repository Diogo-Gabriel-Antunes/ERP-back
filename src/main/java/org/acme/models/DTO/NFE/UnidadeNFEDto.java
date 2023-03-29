package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

@Getter
@Setter
public class UnidadeNFEDto implements DTO {
    private String comercial;
    private String tributavel;
    private long quantidade;
}
