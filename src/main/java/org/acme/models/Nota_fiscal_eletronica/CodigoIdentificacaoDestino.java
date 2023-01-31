package org.acme.models.Nota_fiscal_eletronica;

public enum CodigoIdentificacaoDestino {
    INTERNA("1"), INTERESTADUAL("2"), EXTERIOR("3");

    private String identificador;

    CodigoIdentificacaoDestino(String identificador) {
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }
}
