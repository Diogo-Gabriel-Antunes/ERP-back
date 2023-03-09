package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.MateriaPrima;
import org.acme.models.enums.UnidadeDeMedida;

import java.util.List;

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
