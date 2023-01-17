package org.acme.models.Nota_fiscal_eletronica;

import java.util.List;

public class Cobranca {
    private String numero;
    private Double valorTotal;
    private double valorDesconto;
    private double valorLiquido;
    private List<Parcelas> parcelas;
}
