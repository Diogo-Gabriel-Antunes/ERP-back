package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.OrdemDeProducao;
import org.acme.models.enums.StatusDaProducao;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimesOrdemDeProducaoDTO implements DTO {
    private LocalDateTime time;
    private StatusDaProducao statusDaProducao;
    private OrdemDeProducaoDTO ordemDeProducao;
}
