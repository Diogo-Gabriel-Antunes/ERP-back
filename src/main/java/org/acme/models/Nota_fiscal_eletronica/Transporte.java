package org.acme.models.Nota_fiscal_eletronica;

import java.util.List;

public class Transporte {
    private String modalidadeFrete;
    private Transportador transportador;
    private RetencaoICMS retencaoICMS;
    private Veiculo veiculo;
    private List<Reboque> reboque;
    private List<Volume> volumes;
}
