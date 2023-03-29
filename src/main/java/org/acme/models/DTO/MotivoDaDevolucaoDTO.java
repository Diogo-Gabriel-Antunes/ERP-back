package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Util.InterfacesUtil.DTO;

@Getter
@Setter
public class MotivoDaDevolucaoDTO implements DTO {
    private String uuid;
    @LabelForm("Motivo")
    private String motivo;

}
