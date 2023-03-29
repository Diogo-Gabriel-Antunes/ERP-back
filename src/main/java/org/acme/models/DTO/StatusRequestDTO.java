package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Pedido;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StatusRequestDTO implements DTO {
    private String status;
    private List<PedidoDTO> pedidos;
}
