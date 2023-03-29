package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Cliente;
import org.acme.models.Itens;
import org.acme.models.StatusRequest;

import java.util.List;

@Getter
@Setter
public class PedidoDTO implements DTO {
    private String uuid;
    @LabelForm("Numero do pedido")
    private String numberRequest;
    @Type(Itens.class)
    @LabelForm("Itens")
    private List<ItensDTO> itens;
    @Type(Cliente.class)
    @LabelForm("Cliente")
    private ClienteDTO cliente;
    @LabelForm("Status")
    private StatusRequest status;
    private Double value;
}
