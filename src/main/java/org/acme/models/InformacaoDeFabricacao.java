package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.UnidadeDeMedida;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class InformacaoDeFabricacao extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    private MateriaPrima materiaPrima;
    private double quantidadeNecessaria;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDaQuantidadeGasta;
    private int quantidadeQueFabrica;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida medidaDeCompra;
    @ManyToOne
    @JsonIgnore
    private Fornecedor fornecedorDaMateriaPrima;
    @ManyToMany(mappedBy = "informacaoDeFabricacao")
    @JsonIgnore
    private Set<Produto> produto;
}
