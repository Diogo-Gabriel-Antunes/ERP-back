package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;

@Getter
@Setter
public class SeloControleDTO implements DTO {
    private String codigo;
    private long quantidade;
}
