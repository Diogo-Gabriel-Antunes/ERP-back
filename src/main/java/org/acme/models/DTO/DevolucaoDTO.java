package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.MotivoDaDevolucao;

import java.time.LocalDateTime;

@Getter
@Setter
public class DevolucaoDTO implements DTO{
    private String uuid;
    @Type(value = MotivoDaDevolucao.class)
    @LabelForm("Motivo da Devolução")
    private MotivoDaDevolucaoDTO motivoDevolucao;
    @LabelForm("Data da devolução")
    private LocalDateTime dataDaDevolucao;

    @LabelForm("Pedido")
    private PedidoDTO pedido;
    private String descricao;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime ultimaAtualizacao;
}
