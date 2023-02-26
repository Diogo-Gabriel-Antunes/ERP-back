package org.acme.models.consulta;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Estoque;
import org.acme.models.Produto;

@Getter
@Setter
public class ProdutoEstoque {
    private Produto produto;
    private Estoque estoque;
}
