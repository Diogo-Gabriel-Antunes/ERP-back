package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.OrdemDeProducao;
import org.acme.models.enums.StatusDaProducao;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimesOrdemDeProducaoDTO {
    private LocalDateTime time;
    private StatusDaProducao statusDaProducao;
    private OrdemDeProducaoDTO ordemDeProducao;
}
