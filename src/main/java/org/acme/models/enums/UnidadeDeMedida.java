package org.acme.models.enums;

public enum UnidadeDeMedida {
    METROS("METROS"), CENTIMETROS("CENTIMETROS"), POLEGADAS("POLEGADAS"),
    KG("KG"), GRAMAS("GRAMAS"), LITROS("LITROS"), GALOES("GALOES");
    private String unidade;

    UnidadeDeMedida(String unidade) {
        this.unidade = unidade;
    }
}
