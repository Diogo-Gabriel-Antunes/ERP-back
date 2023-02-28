package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDeLoteDTO implements DTO {
    private String uuid;
    private String tipoLote;
    private int quantidadeMaxEmLote;
}
