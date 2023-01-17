package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Category;
import org.acme.models.Imagem;
import org.acme.models.Imposto;
import org.acme.models.enums.Status;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProductDTO implements DTO {
    private String nome;
    private String codigoDeBarras;
    private LocalDate dataCriacao;
    private LocalDate dataAlteracao;
    private boolean status;
    private List<Imposto> imposto;
    private Category categoria;
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double precoForncedor;
}
