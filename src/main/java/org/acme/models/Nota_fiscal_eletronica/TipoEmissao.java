package org.acme.models.Nota_fiscal_eletronica;

public enum TipoEmissao {
    NORMAL("1"), FSIA("2"), SCAN("3"), DPEC("4"), FSDA("5"), SVCAN("6"), SVCRS("7");

    private String tipoEmissao;

    public String getTipoEmissao() {
        return this.tipoEmissao;
    }

    TipoEmissao(String tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }
}
