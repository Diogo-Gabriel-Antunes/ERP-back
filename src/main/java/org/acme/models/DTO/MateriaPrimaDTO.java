package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;

@Getter
@Setter
public class MateriaPrimaDTO implements DTO{
    private String uuid;
    @LabelForm("Nome")
    private String nome;
    @LabelForm("Descrição")
    private String descricao;
    @LabelForm("Quantidade")
    private int quantidade;
    @LabelForm("Preço unitario")
    private double precoUnitario;
}
