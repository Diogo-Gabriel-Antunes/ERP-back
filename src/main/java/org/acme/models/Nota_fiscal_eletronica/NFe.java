package org.acme.models.Nota_fiscal_eletronica;

import org.acme.models.Itens;


import java.time.LocalDateTime;
import java.util.List;

public class NFe {

    private String idIntegracao;
    private String versaoManual;
    private String codigo;
    private Integer serie;
    private Finalidade finalidade;
    private String natureza;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataSaidaEntrada;
    private boolean saida;
    private boolean presencial;
    private TipoImpressao tipoImpressao;
    private TipoEmissao tipoEmissao;
    private CodigoIdentificacaoDestino codigoIdentificacaoDestino;
    private String codigoMunicipioFatoGerador;
    private boolean consumidorFinal;
    private Emitente emitente;
    private Destinatario destinatario;
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
    private Unidade unidade;
    private ValorUnitario valorUnitario;
    private List<Medicamentos> medicamentos;
    private Veiculo veiculo;
    private List<Armamento> armamentos;
    private Combustivel combustivel;
    private Total total;
    private Transporte transporte;
    private List<Pagamento> pagamentos;
    private Cobranca cobranca;
    private String informacoesComplementaresContribuinte;
    private String informacoesComplementares;
    private boolean enviarEmail;
    private Local localEntrega;
    private Local localRetirada;
    private Exportacao exportacao;
    private IntermediadorTransacao intermediadorTransacao;
    private int intermediador;
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
class Unidade{
    private String comercial;
    private String tributavel;
    private long quantidade;
    private double valor;
}
class ValorUnitario{
    private String comercial;
    private String tributavel;
}