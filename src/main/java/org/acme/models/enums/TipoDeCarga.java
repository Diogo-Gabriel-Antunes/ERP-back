package org.acme.models.enums;

public enum TipoDeCarga {
    EXTERNA("Externa"), INTERNA("Interna"),MISTA("Mista ");

    private String tipo;

    public String getTipo() {
        return tipo;
    }

    TipoDeCarga(String tipo) {
        this.tipo = tipo;
    }
}
