package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Itens;
import org.acme.models.ItensExternos;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemMovimentadoDTO implements DTO {
    private String uuid;
    private ItensExternos itensExternos;
    private Itens itens;
    private Long quantidade;
    private LocalDateTime dataCriacao;
}
