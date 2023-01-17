package org.acme.models.Nota_fiscal_eletronica;

public class IPI {
    private String cnpjProdutor;
    private SeloControle seloControle;
    private String codigoEnquadramentoLegal;
    private String cst;
    private double baseCalculo;
    private double aliquota;
    private Unidade unidade;
    private double valor;
}

class SeloControle{
    private String codigo;
    private long quantidade;
}
