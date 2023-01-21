package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Itens;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
public class NFe {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
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
    @ManyToOne
    private Pessoa emitente;
    @ManyToOne
    private Pessoa destinatario;
    @OneToMany
    private List<Itens> itens;
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
    @OneToOne
    private UnidadeNFE unidade;
    @ManyToOne
    private ValorUnitario valorUnitario;
    @OneToMany
    private List<Medicamentos> medicamentos;
    @ManyToOne
    private Veiculo veiculo;
    @OneToMany
    private List<Armamento> armamentos;
    @ManyToOne
    private Combustivel combustivel;
    @OneToOne
    private Total total;
    @OneToOne
    private Transporte transporte;
    @OneToMany
    private List<Pagamento> pagamentos;
    @ManyToOne
    private Cobranca cobranca;
    private String informacoesComplementaresContribuinte;
    private String informacoesComplementares;
    private boolean enviarEmail;
    @OneToOne
    private Local localEntrega;
    @OneToOne
    private Local localRetirada;
    @OneToOne
    private Exportacao exportacao;
    @OneToOne
    private IntermediadorTransacao intermediadorTransacao;
    private int intermediador;
    @OneToOne
    private Local responsavelTenico;
}

enum Finalidade{
    NFE_NORMAL("1"),NFECOMPLEMENTAR("2"),NFEAJUSTE("3"),NFEDEVOLUCAO("4");

    private String finalidade;

    public String getFinalidade(){
        return this.finalidade;
    }
    Finalidade(String finalidade){
        this.finalidade = finalidade;
    }
}
enum TipoImpressao{
    SEMDANFE("0"),RETRATO("1"),PAISAGEM("2"),SIMPLIFICADO("3");

    private String tipoImpressao;
    public String getTipoImpressao(){
        return this.tipoImpressao;
    }
    TipoImpressao(String tipoImpressao){
        this.tipoImpressao = tipoImpressao;
    }
}

enum TipoEmissao{
    NORMAL("1"),FSIA("2"),SCAN("3"),DPEC("4"),FSDA("5"),SVCAN("6"),SVCRS("7");

    private String tipoEmissao;
    public String getTipoEmissao(){
        return this.tipoEmissao;
    }
    TipoEmissao(String tipoEmissao){
        this.tipoEmissao = tipoEmissao;
    }
}


enum CodigoIdentificacaoDestino {
    INTERNA("1"), INTERESTADUAL("2"), EXTERIOR("3");

    private String identificador;

    CodigoIdentificacaoDestino(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }
}

