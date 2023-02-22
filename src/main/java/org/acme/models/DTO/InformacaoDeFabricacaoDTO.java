package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.MateriaPrima;
import org.acme.models.enums.UnidadeDeMedida;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class InformacaoDeFabricacaoDTO implements DTO {
    String uuid;
    @Type(MateriaPrima.class)
    MateriaPrimaDTO materiaPrima;
    double quantidadeNecessaria;

    UnidadeDeMedida unidadeDaQuantidadeGasta;
    int quantidadeQueFabrica;

    UnidadeDeMedida medidaDeCompra;
    @Type(List.class)
    List<ProdutoDTO> produto;
}
