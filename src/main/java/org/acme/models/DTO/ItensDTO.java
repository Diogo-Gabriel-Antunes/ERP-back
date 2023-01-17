package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Compra;
import org.acme.models.Product;
import org.acme.models.Request;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class ItensDTO implements DTO {
    private ProductDTO product;
    private Long quantidade;
    private Request pedido;
    private Set<Compra> compras;
    private LocalDate dataAtualizacao;
    private LocalDate dataCriacao;
}
