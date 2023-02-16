package org.acme.models.DTO;

import org.acme.models.Pedido;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

public class StatusRequestDTO {
    private String status;
    private List<PedidoDTO> pedidos;
}
