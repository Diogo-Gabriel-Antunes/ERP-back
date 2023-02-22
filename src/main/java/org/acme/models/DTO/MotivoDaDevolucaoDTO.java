package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotivoDaDevolucaoDTO implements DTO{
    private String uuid;
    private String motivo;
}
