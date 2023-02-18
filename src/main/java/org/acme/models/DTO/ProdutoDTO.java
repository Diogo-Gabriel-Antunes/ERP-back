package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Category;
import org.acme.models.Imagem;
import org.acme.models.Imposto;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProdutoDTO implements DTO {
    private String uuid;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String codigo;
    private boolean status;
    private List<Imposto> imposto;
    private Category categoria;
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoOriginal;
    @Column(nullable = true)
    private long quantidadeMinima;

}
