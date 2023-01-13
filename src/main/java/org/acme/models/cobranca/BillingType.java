package org.acme.models.cobranca;

public enum BillingType {

    BOLETO("Boleto"),CREDIT_CARD("Cartão de credito"),PIX("Pix"),UNDEDFINED("Indefinido"),DEBIT_CARD("Cartão de debito");

    private String tipo;

    BillingType(String tipo){
        this.tipo = tipo;
    }
    public String getTipo(){
        return this.tipo;
    }
}
