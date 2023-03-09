package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.ItemMovimentado;
import org.acme.models.Motorista;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.models.enums.TipoDeCarga;

import java.util.List;

@Getter
@Setter
public class MontagemDeCargaDTO implements DTO {
    private String uuid;
    @Type(List.class)
    private List<ItensDTO> itens;
    private TipoDeCarga tipoDeCarga;
    @Type(Transportador.class)
    private TransportadorDTO transportador;
    @Type(Veiculo.class)
    private VeiculoDTO veiculo;
    private Boolean isManual;
    @Type(Motorista.class)
    private MotoristaDTO motorista;
    private List<ItemMovimentadoDTO> itemMovimentados;


}
