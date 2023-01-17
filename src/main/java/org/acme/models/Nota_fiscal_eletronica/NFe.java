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