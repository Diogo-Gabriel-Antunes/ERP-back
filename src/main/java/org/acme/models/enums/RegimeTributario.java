package org.acme.models.enums;

public enum RegimeTributario {

    NENHUM(0),SIMPLESNACIONAL(1),SIMPLESEXCESSO(2),REGIMENORMAL(3),NORMAL(4);

    int regime;
    RegimeTributario(int regime){
        this.regime = regime;
    }
}
