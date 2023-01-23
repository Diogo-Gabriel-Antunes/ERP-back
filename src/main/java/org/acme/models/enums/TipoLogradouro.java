package org.acme.models.enums;

public enum TipoLogradouro {
    Alameda("Alameda"), Avenida("Avenida"), Chácara("Chácara"), Colônia("Colônia"),
    Condomínio("Condomínio"), Estância("Estância"), Estrada("Estrada"), Fazenda("Fazenda"),
    Praça("Praça"), Prolongamento("Prolongamento"), Rodovia("Rodovia"), Rua("Rua"),
    Sítio("Sítio"), Travessa("Travessa"), Vicinal("Vicinal"), Eqnp("Eqnp");

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }

    TipoLogradouro(String tipo) {
        this.tipo = tipo;
    }


}
