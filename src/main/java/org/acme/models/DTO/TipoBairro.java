package org.acme.models.DTO;

public enum TipoBairro {
    Bairro("Bairro"), Bosque("Bosque"), Chácara("Chácara"), Conjunto("Conjunto"),
    Desmembramento("Desmembramento"), Distrito("Distrito"), Favela("Favela"), Fazenda("Fazenda"),
    Gleba("Gleba"), Horto("Horto"), Jardim("Jardim"), Loteamento("Loteamento"), Núcleo("Núcleo"),
    Parque("Parque"), Residencial("Residencial"), Sítio("Sítio"), Tropical("Tropical"), Vila("Vila"),
    Zona("Zona"), Centro("Centro"), Setor("Setor");

    private String tipo;

    public String getTipo(){
        return this.tipo;
    }
    TipoBairro(String tipo) {
        this.tipo = tipo;
    }
}
