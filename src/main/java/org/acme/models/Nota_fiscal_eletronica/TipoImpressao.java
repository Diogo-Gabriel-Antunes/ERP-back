package org.acme.models.Nota_fiscal_eletronica;

public enum TipoImpressao {
    SEMDANFE("0"), RETRATO("1"), PAISAGEM("2"), SIMPLIFICADO("3");

    private String tipoImpressao;

    public String getTipoImpressao() {
        return this.tipoImpressao;
    }

    TipoImpressao(String tipoImpressao) {
        this.tipoImpressao = tipoImpressao;
    }
}
