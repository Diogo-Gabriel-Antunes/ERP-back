package org.acme.models.Nota_fiscal_eletronica;

public enum ViaTransporte {
    MARITIMA(1), FLUVIAL(2), LACUSTRE(3), AEREA(4), POSTAL(5),
    FERROVIARIA(6), RODOVIARIA(7), CONDUTO_REDE_TRANSMISSAO(8), MEIOS_PROPRIOS(9),
    ENTRADA_SAIDA_FICTA(10), COURIER(11), HANDCARRY(12);

    private final int valor;

    ViaTransporte(int valor) {
        this.valor = valor;
    }

    public int getValue() {
        return valor;
    }
}
