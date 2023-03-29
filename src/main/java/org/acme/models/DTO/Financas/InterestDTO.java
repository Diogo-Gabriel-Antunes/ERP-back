package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

@Getter
@Setter
public class InterestDTO implements DTO {
    private double value;
}
