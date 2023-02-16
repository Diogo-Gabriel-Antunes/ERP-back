package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Category;
import org.acme.models.Imagem;
import org.acme.models.Imposto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProdutoDTO implements DTO {
    private  String uuid;
    private String nome;
    private boolean status;
    private Category categoria;
    private Double precoUnitario;
    private long quantidadeMinina;
}
