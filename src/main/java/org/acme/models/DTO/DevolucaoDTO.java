package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.MotivoDaDevolucao;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevolucaoDTO implements DTO{
    private String uuid;
    private MotivoDaDevolucao motivoDevolucao;
    private LocalDateTime dataDaDevolucao;

    private PedidoDTO pedido;
    private String descricao;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime ultimaAtualizacao;
}
