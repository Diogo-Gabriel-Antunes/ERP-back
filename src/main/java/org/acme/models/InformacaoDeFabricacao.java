package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.UnidadeDeMedida;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Setter
@Entity
@Getter
public class InformacaoDeFabricacao extends PanacheEntityBase implements Model {
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
    @ManyToMany(mappedBy = "informacaoDeFabricacao")
    @JsonbTransient
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Produto> produto;

    public InformacaoDeFabricacao() {
        produto = new HashSet<>();
        materiaPrima = new MateriaPrima();
    }
}
