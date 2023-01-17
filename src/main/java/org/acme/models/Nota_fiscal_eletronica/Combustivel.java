package org.acme.models.Nota_fiscal_eletronica;

public class Combustivel {
    private String codigoAnp;
    private String descricaoAnp;
    private Double percentualGlp;
    private Double percentualGnn;
    private double percentualGni;
    private double valorPartida;
    private String codigoAutorizacao;
    private long faturamentoTemperaturaAmbiente;
    private String estadoConsumo;
    private Cide cide;
    private Encerrante encerrante;
}
class Cide{
    private double baseCalculo;
    private double aliquota;
    private double valor;
}
class Encerrante{
    private String numeroBico;
    private String numeroBomba;
    private String numeroTanque;
    private double valorInicio;
    private double valorFinal;
}