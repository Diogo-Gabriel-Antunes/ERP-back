package org.acme.models.enums;

public enum PrioridadeCarga {
    MUITO_ALTA(0.40), ALTA(0.30), MEDIA(0.15), BAIXA(0.10)
    , MUITO_BAIXA(0.05);

    private Double prioridade;

    public Double getPrioridade(){
        return this.prioridade;
    }
    PrioridadeCarga(Double prioridade) {
        this.prioridade = prioridade;
    }
}
