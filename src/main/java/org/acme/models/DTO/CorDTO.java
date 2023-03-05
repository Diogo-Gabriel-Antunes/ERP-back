package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorDTO implements DTO {
    private String uuid;
    private String codigoCor;
    private String descricaoCor;
}
