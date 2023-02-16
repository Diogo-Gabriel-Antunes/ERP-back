package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Cliente;
import org.acme.models.Itens;
import org.acme.models.StatusRequest;

import java.util.List;

@Getter
@Setter
public class PedidoDTO implements DTO {
    private String uuid;
    private String numberRequest;
    @Type(Itens.class)
    private List<Itens> itens;
    @Type(Cliente.class)
    private ClienteDTO cliente;
    private StatusRequest status;
    private Double value;
}
