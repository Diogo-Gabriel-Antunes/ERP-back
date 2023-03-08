package org.acme.models.enums;

public enum PrioridadeCarga {
    MUITO_ALTA("Muito alta"), ALTA("Alta"), MEDIA("Media"), BAIXA("Baixa")
    , MUITO_BAIXA("Muito Baixa");

    private String prioridade;

    public String getPrioridade(){
        return this.prioridade;
    }
    PrioridadeCarga(String prioridade) {
        this.prioridade = prioridade;
    }
}
