package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.DTO;

@Getter
@Setter
public class InterestDTO implements DTO {
    private double value;
}
