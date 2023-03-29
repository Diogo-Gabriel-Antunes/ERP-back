package org.acme.models.Frete;

public enum TipoTabela {
    CLIENTE("CLIENTE"),
    FILIAL("FILIAL"),
    AGENTE("AGENTE"),
    PARCEIRO("PARCEIRO"),
    TRANSPORTADOR_SUBCONTRATADO("TRANSPORTADOR_SUBCONTRATADO");

    private String tipo;

    TipoTabela(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}
