package org.acme.models.enums;

import lombok.Getter;
import lombok.Setter;

public enum Prioridade {
    BAIXA("Baixa"),MEDIA("Media"),ALTA("Alta"),URGENTE("Urgente");
    private String prioridade;

    Prioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
