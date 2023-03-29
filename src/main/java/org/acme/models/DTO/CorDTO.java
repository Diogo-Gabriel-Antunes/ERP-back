package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

@Getter
@Setter
public class CorDTO implements DTO {
    private String uuid;
    private String codigoCor;
    private String descricaoCor;
}
