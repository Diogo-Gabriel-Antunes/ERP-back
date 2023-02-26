package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Compra;
import org.acme.models.Pedido;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ItensDTO implements DTO {
    private String uuid;
    private ProdutoDTO produto;
    private Long quantidade;
}
