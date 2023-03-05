package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Cliente;
import org.acme.models.Compra;
import org.acme.models.Pedido;
import org.acme.models.Produto;

import javax.ws.rs.client.Client;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ItensDTO implements DTO {
    private String uuid;
    @Type(Produto.class)
    private ProdutoDTO produto;
    private Long quantidade;
    @Type(Cliente.class)
    private ClienteDTO cliente;
}
