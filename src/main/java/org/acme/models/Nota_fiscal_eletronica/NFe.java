package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.Itens;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class NFe implements Model {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private String idIntegracao;
    private String versaoManual;
    private String codigo;
    private String chave;
    private Integer serie;
    @Enumerated(EnumType.STRING)
    private Finalidade finalidade;
    private String natureza;
    private String dataEmissao;
    private String dataSaidaEntrada;
    private boolean saida;
    private boolean presencial;
    @Enumerated(EnumType.STRING)
    private TipoImpressao tipoImpressao;
    @Enumerated(EnumType.STRING)
    private TipoEmissao tipoEmissao;
    @Enumerated(EnumType.STRING)
    private CodigoIdentificacaoDestino codigoIdentificacaoDestino;
    private String codigoMunicipioFatoGerador;
    private boolean consumidorFinal;
    private boolean compoeTotal;
    private String nve;
    private String indicadorEscalaRelevante;
    private String cnpjFabricante;
    private String codigoBeneficioFiscal;
    private String exTipi;
    private double valorFrete;
    private double valorSeguro;
    private double valorOutros;
    private String numeroFci;
    private String informacoesComplementaresContribuinte;
    private String informacoesComplementares;
    private boolean enviarEmail;
    private int intermediador;
    @OneToOne
    @JsonbTransient
    private UnidadeNFE unidade;
    @ManyToOne
    @JsonbTransient
    private ValorUnitario valorUnitario;
    @OneToMany
    @JsonbTransient
    private List<Medicamentos> medicamentos;
    @ManyToOne
    private Veiculo veiculo;
    @OneToMany(mappedBy = "nFe")
    @JsonbTransient
    private List<Armamento> armamentos;
    @ManyToOne
    @JsonbTransient
    private Combustivel combustivel;
    @OneToOne
    @JsonbTransient
    private Total total;
    @OneToOne
    @JsonbTransient
    private Transporte transporte;
    @OneToMany
    @JsonbTransient
    private List<Pagamento> pagamentos;
    @ManyToOne
    @JsonbTransient
    private Cobranca cobranca;
    @OneToOne
    @JsonbTransient
    private Local localEntrega;
    @OneToOne
    @JsonbTransient
    private Local localRetirada;
    @OneToOne
    @JsonbTransient
    private Exportacao exportacao;
    @OneToOne
    @JsonbTransient
    private IntermediadorTransacao intermediadorTransacao;
    @OneToOne
    @JsonbTransient
    private Local responsavelTenico;
    @ManyToOne
    @JsonbTransient
    private Pessoa emitente;
    @ManyToOne
    @JsonbTransient
    private Pessoa destinatario;
    @OneToMany
    @JsonbTransient
    private List<Itens> itens;
}


