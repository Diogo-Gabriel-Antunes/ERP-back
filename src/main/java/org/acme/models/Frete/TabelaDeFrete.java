package org.acme.models.Frete;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TabelaDeFrete extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private Integer codigo;
    private String descricao;

    private String url;

    private Boolean inativo;

    @Enumerated(EnumType.STRING)
    private Modal modal;

    @Enumerated(EnumType.STRING)
    private TipoTabela tipoTabela;


    private Boolean todosOsProdutos;

    private TipoTributacao tributacao;

    private Boolean rateiaTributacao;

    private String campoRateioTributacao;

    private TipoCobrancaPedagio tipoCobrancaPedagio;

    private Double valorCobrancaFaixaDeKG;


    @Enumerated(EnumType.STRING)
    private TipoFrete tipoFrete;


    @Enumerated(EnumType.STRING)
    private Segmento segmento;

    private Boolean fobDirigido;

    private Boolean inclusiveClienteComTabela;

    private Boolean somenteFobDirigido;

    private Boolean tabelaPadrao;

    public enum TipoTributacao {
        NAO_INCLUSA, INCLUSA
    }

    public enum Segmento {
        LOTACAO, FRACIONADO
    }

    public enum TipoFrete {
        CIF("CIF"),
        FOB("FOB"),
        AMBOS("Ambos");

        public String tipoFrete;

        TipoFrete(String descricao) {
            this.tipoFrete = descricao;
        }

        public String getTipoFrete() {
            return this.tipoFrete;
        }

    }

    public enum TipoCobrancaPedagio {
        COBRA_POR_FAIXA, COBRA_POR_CONHECIMENTO, COBRA_POR_PESO, COBRA_POR_FAIXA_M3
    }
}

