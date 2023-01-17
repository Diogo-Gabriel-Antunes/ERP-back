package org.acme.models.Nota_fiscal_eletronica;

import java.util.List;

public class ImportacaoDados {
    private String numero;
    private String dataEmissao;
    private ViaTransporte viaTransporte;
    private Double valorAfrmm;
    private int formaImportacao;
    private String codigoExportador;
    private Desembaraco desembaraco;
    private Adquirente adquirente;
    private List<Adicoes> adicoes;
}

enum ViaTransporte{
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
